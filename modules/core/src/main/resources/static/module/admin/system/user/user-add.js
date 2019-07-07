layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form', 'upload' , 'laydate' ,'layedit' ,'set'], function () {
    var $ = layui.$
        , form = layui.form,
        layer = layui.layer,
        setter = layui.setter,
        upload = layui.upload,
        laydate = layui.laydate;

    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    //监听form提交
    form.on('submit(LAYF-user-form-add-submit)', function (data) {
        var field = data.field; //获取提交的字段
        // field.content = editor.txt.html();
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/user/add.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                // console.log(res);
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg,{
                        icon: 1,
                        time: 2000
                    });
                    parent.layui.table.reload('userTable'); //重载表格
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg,{
                        icon: 5,
                        time: 2000
                    });
                }

            }
        });
    });

    form.verify({
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });

    //laydate常规用法
    laydate.render({
        elem: '#user-birthday',
        type: 'date',
        trigger: 'click',
        format: 'yyyy/MM/dd'
    });

    //渲染部门下拉选择
    $.ajax({
        url: setter.ctxPath + '/dept/list.ajax',
        data: '',
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#dept-select');
                $select.append('<option value="">请选择</option>')
                for (var i in listData){
                    $select.append('<option value="'+listData[i].id+'">'+listData[i].fullName+'</option>')
                }
                form.render('select','LAYF-user-form-add'); //刷新select选择框
            }
        }
    });
})