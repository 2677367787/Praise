var Flow = function () {
    var lastFocus;
    var ssjType;
    var needSelect = ['2251691379292','5005191133','266619228780','416709790625','3133093188'];
    return {
        initType:function(){
            $.ajax({
                url: "/ssj/type",
                dataType: "json",
                async: true,
                data: { "pageSize": "10", "pageNum": "1"}, //传参的的参数
                type: "GET",   //请求方式类型
                beforeSend: function() {
                    //请求前的处理操作
                },
                success: function(result,textStatus) { //此处data为返回值
                    //请求成功时处理操作
                    if(result.code === 200){
                        ssjType = result.data.list;
                        console.log(ssjType);
                    }else{
                        console.log(data.message);
                    }
                },
                complete: function() {
                    //请求完成的处理操作
                },
                error: function() {
                    //请求出错处理操作
                }
            });
        },
        query:function(){
            Flow.queryData(1);
        },

        queryData:function(pageNum){
            var isSendSsj = $('input[name="isSendSsj"]:checked').val();

            var data = { "pageSize": "10", "pageNum": pageNum,"isSendSsj":isSendSsj};


            $.ajax({
                url: "/account/flow/page",
                dataType: "json",
                async: true,
                data: data, //传参的的参数
                type: "GET",   //请求方式类型
                beforeSend: function() {
                    //请求前的处理操作
                },
                success: function(result,textStatus) { //此处data为返回值
                    //请求成功时处理操作
                    if(result.code === 200){
                        var obj = $("#flowBody");
                        obj.empty();
                        var html = [];
                        for (var i = 0; i < result.data.list.length; i++){
                            var f = result.data.list[i];
                            html.push("<tr>");
                            html.push("<td>" + (i+1) + "</td>>");
                            html.push("<td>" + f.id + "</td>>");
                            html.push("<td>" + f.store + "</td>>");
                            html.push("<td>" + f.money + "</td>>");
                            html.push("<td>" + moment(f.tradeDate).format('YYYY-MM-DD H:mm:ss') + "</td>>");
                            html.push("<td>" + (f.isSendSsj === 1?'是':'否') + "</td>>");

                            if($.inArray(f.ssjType,needSelect) >= 0) {
                                var ssjTypeSelect = [];
                                ssjTypeSelect.push('<select class="selectpicker bla bla bli" data-live-search="true">');
                                var rareList = ssjType.filter(function (map) {
                                    return map.weight === 0
                                });

                                var oftenList = ssjType.filter(function (map) {
                                    return map.weight !== 0
                                });

                                for (var j = 0; j < oftenList.length; j++) {
                                    ssjTypeSelect.push('<option value="' + oftenList[j].typeId + '"');
                                    if (oftenList[j].typeId === f.ssjType) {
                                        ssjTypeSelect.push(' selected="true"');
                                    }
                                    ssjTypeSelect.push('>');
                                    ssjTypeSelect.push(oftenList[j].typeName + '</option>');
                                }

                                for (var j = 0; j < rareList.length; j++) {
                                    if (j === 0) {
                                        ssjTypeSelect.push('<optgroup label="" data-subtext="不常用" data-icon="icon-ok">');
                                    }
                                    ssjTypeSelect.push('<option value="' + rareList[j].typeId + '"');
                                    if (rareList[j].typeId === f.ssjType) {
                                        ssjTypeSelect.push(' selected="true"');
                                    }
                                    ssjTypeSelect.push('>');
                                    ssjTypeSelect.push(rareList[j].typeName + '</option>');
                                    if (j === rareList.length - 1) {
                                        ssjTypeSelect.push('</optgroup>');
                                    }
                                }
                                ssjTypeSelect.push('</select>');
                                html.push("<td>" + ssjTypeSelect.join("") + "</td>>");
                            }else{
                                html.push("<td>" + f.ssjTypeName + f.ssjType + "</td>>");
                            }
                            html.push('<td><a class="save" href="javascript:;">Save</a></td>');
                            html.push("</tr>");
                        }
                        obj.append(html.join(""));

                        var pagination = $("#pagination");
                        pagination.empty();
                        html = [];
                        html.push('<li class="prev disabled"><a href="#">← Prev</a></li>');
                        var navigatepageNums = result.data.navigatepageNums;
                        for(var i = 0; i < navigatepageNums.length; i++){
                            html.push('<li data-value="' + navigatepageNums[i] + '" ');
                            if(navigatepageNums[i] === pageNum){
                                html.push('class="active"');
                            }
                            html.push('>');
                            html.push('<a href="#">' + navigatepageNums[i] + '</a></li>');
                        }
                        html.push('<li class="next"><a href="#">Next → </a></li>');
                        pagination.append(html.join(""));

                        $('.selectpicker').selectpicker({
                            'selectedText': 'cat'
                        });
                    }else{
                        console.log(data.message);
                    }
                },
                complete: function() {
                    //请求完成的处理操作
                },
                error: function() {
                    //请求出错处理操作
                }
            });
        },
        //main function to initiate the module
        init: function () {
            $('#pagination').on('click', 'li',function (e) {

                if(!lastFocus) {
                    lastFocus = $('#pagination li:eq(1)');
                }

                var pageNum = Number($(this).attr("data-value"));
                Flow.queryData(pageNum);
            });

            $('#editable-sample').on('click','a.save',function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var $tr = $(this).parents('tr');
                var id = $tr.children("td:eq(1)").text();
                var ssjType = $tr.find("select").val();

                // var data = {};
                // data.id = id;
                // data.ssjType = ssjType;
                // JSON.stringify(data)
                $.ajax({
                    url: "/account/flow/update",
                    dataType: "json",
                    contentType : 'application/json',
                    async: true,
                    data: JSON.stringify({ "id": id, "ssjType": ssjType}), //传参的的参数
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
            });
        },
        synEmail:function () {
            $.ajax({
                url: "/email/syn",
                dataType: "json",
                contentType : 'application/json',
                async: true,
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
        },
        match:function () {
            $.ajax({
                url: "/ssj/match",
                dataType: "json",
                contentType : 'application/json',
                async: true,
                type: "get",   //请求方式类型
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
        }

    };

}();