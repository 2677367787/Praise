var EditableTable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" readonly="true" class="form-control small" value="' + aData[0] + '">';

                var value = aData[2];
                jqTds[2].innerHTML = '<label class="checkbox-inline"><input name="type" type="radio" value="1" ' + (value === '等于' ? ' checked ':'') + '/>等于</label>'+
                    '<label class="checkbox-inline"><input name="type" type="radio" value="2" ' + (value === '包含' ? ' checked ':'') +'/>包含</label>';

                jqTds[1].innerHTML = '<input type="text" readonly="true" class="form-control small" value="' + aData[1] + '">';
                jqTds[3].innerHTML = '<input type="text" class="form-control small" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<input type="text" class="form-control small" value="' + aData[4] + '">';
                jqTds[5].innerHTML = '<input type="text" class="form-control small" value="' + aData[5] + '">';
                jqTds[6].innerHTML = '<a class="edit" href="">Save</a>';
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input[type="text"]', nRow);

                var ssjType = $('input[name="type"]:checked',nRow).val() === '1' ? '等于':'包含';
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(ssjType, nRow, 2, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 3, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 4, false);
                oTable.fnUpdate(jqInputs[4].value, nRow, 5, false);

                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 6, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
                oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
                oTable.fnDraw();
            }

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '', '', '','','',
                        '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#editable-sample a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("Are you sure to delete this row ?") == false) {
                    return;
                }

                var nRow = $(this).parents('tr')[0];
                oTable.fnDeleteRow(nRow);

                var relationId = $(nRow).find('td:eq(1)').text();
                $.ajax({
                    url: "/relation/"+relationId,
                    dataType: "json",
                    contentType : 'application/json',
                    async: true,
                    type: "delete",   //请求方式类型
                    beforeSend: function() {
                        //请求前的处理操作
                    },
                    success: function(result,textStatus) { //此处data为返回值
                        //请求成功时处理操作
                        if(result.code === 200){
                            alert(result.message);
                        }else{
                            console.log(result);
                        }
                    },
                    complete: function() {
                        //请求完成的处理操作
                    },
                    error: function() {
                        //请求出错处理操作
                    }
                });

            });

            $('#editable-sample a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#editable-sample a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                    var jqInputs = $('input[type="text"]', nEditing);
                    var type = $('input[name="type"]:checked',nRow).val();
                    console.log(jqInputs);
                    saveRow(oTable, nEditing);
                    console.log(jqInputs);
                    var data = { "relationId": jqInputs[1].value, "keyWords": jqInputs[2].value,"ssjType":jqInputs[3].value,
                        "ssjTypeName":jqInputs[4].value,"type":type};
                    console.log(data);
                    nEditing = null;
                    $.ajax({
                        url: "/relation",
                        dataType: "json",
                        contentType : 'application/json',
                        async: true,
                        data: JSON.stringify(data), //传参的的参数
                        type: "post",   //请求方式类型
                        beforeSend: function() {
                            //请求前的处理操作
                        },
                        success: function(result,textStatus) { //此处data为返回值
                            //请求成功时处理操作
                            if(result.code === 200){
                                alert(result.message);
                            }else{
                                console.log(result);
                            }
                        },
                        complete: function() {
                            //请求完成的处理操作
                        },
                        error: function() {
                            //请求出错处理操作
                        }
                    });
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();