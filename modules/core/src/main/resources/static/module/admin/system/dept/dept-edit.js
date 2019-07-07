layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form'], function () {
    var $ = layui.$,
        form = layui.form,
        setter = layui.setter,
        layer = layui.layer;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    form.val('LAYF-dept-form-edit', {
        id: Swear.formValue.id,
        fullName: Swear.formValue.fullName,
        simpleName: Swear.formValue.simpleName,
        sort: Swear.formValue.sort,
        pid: Swear.formValue.pid
    })

    //监听提交
    form.on('submit(LAYF-dept-form-edit-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/dept/edit.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg);
                    // parent.layui.table.reload('deptTable'); //重载表格
                    parent.renderTable();
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg);
                }

            }
        });
    });

    //渲染父级菜单下拉选择
    $.ajax({
        url: setter.ctxPath + '/dept/list.ajax',
        data: '',
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var list = res.data;
                var $select = $('#parent-dept-select');
                $select.append('<option value="">请选择</option>')
                for (var i in list){
                    if (list[i].id != Swear.formValue.id) {
                        $select.append('<option value="' + list[i].id + '">' + list[i].fullName + '</option>')
                    }
                }
                form.val('LAYF-dept-form-edit',{
                    pid: Swear.formValue.pid
                });
                form.render('select','LAYF-dept-form-edit'); //刷新select选择框
            }
        }
    });
})