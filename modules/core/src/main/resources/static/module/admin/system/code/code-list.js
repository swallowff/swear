layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'jquery', 'admin'], function () {
    var form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    $.ajax({
        url: setter.ctxPath + "/sys/code/tableList",
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#table-select');
                $select.append('<option value="">请选择数据表</option>');
                for (var i in listData){
                    $select.append('<option value="'+listData[i].tableName+'">'+listData[i].tableName+'</option>')
                }
                form.render('select','LAYF-code-gen-form'); //刷新select选择框
            }
        }

    });

    //监听表单提交
    form.on('submit(LAYF-code-gen)', function (data) {
        var field = data.field;
        if (field.forceCover == 1) {
            layer.confirm("您已选择强制覆盖原文件,确定要继续此操作吗?",function (index) {
                executeGenCode(field)
            });
        }else {
            layer.confirm("即将生成代码,确认继续吗?",function (index) {
                executeGenCode(field)
            })
        }



    });

    function executeGenCode(field) {
        $.ajax({
            url: setter.ctxPath + "/sys/code/execute",
            method: 'POST',
            data: field,
            success: function (res) {
                console.log(res)
                if (res.code == setter.response.statusCode.ok) {
                    layer.msg(res.msg,{
                        icon: 1,
                        time: 1800
                    })
                }else {
                    layer.msg(res.msg,{
                        icon: 5,
                        time: 1800
                    })
                }

            }
        })
    }







});