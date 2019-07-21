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

    form.val('LAYF-csUser-form-edit', {
        id: Swear.formValue.id,
        sysUid: Swear.formValue.sysUid,
        avatar: Swear.formValue.avatar,
        userName: Swear.formValue.userName,
        nickName: Swear.formValue.nickName,
        password: Swear.formValue.password,
        telephone: Swear.formValue.telephone,
        email: Swear.formValue.email,
        sign: Swear.formValue.sign,
        isOnline: Swear.formValue.isOnline,
        lastLoginIp: Swear.formValue.lastLoginIp,
        status: Swear.formValue.status,
    })

    //监听提交
    form.on('submit(LAYF-csUser-form-edit-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/cs/csUser/edit.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg,{
                        icon: 1,
                        time: 2000
                    });
                    parent.layui.table.reload('csUserTable'); //重载表格
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
})