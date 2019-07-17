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

    form.val('LAYF-menu-form-edit', {
        id: Swear.formValue.id,
        name: Swear.formValue.name,
        code: Swear.formValue.code,
        pid: Swear.formValue.pid,
        icon: Swear.formValue.icon,
        url: Swear.formValue.url,
        sort: Swear.formValue.sort,
        levels: Swear.formValue.levels,
        isMenu: Swear.formValue.isMenu,
        isExpandable: Swear.formValue.isExpandable,
        tips: Swear.formValue.tips
    })

    //监听提交
    form.on('submit(LAYF-menu-form-edit-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/menu/edit.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg, {
                        icon: 1,
                        time: 1800
                    });
                    parent.layui.table.reload('menuTable'); //重载表格
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg, {
                        icon: 5,
                        time: 1800
                    });
                }

            }
        });
    });

    //渲染父级菜单下拉选择
    $.ajax({
        url: setter.ctxPath + '/menu/list.ajax',
        data: {
            isMenu: true,
            page: -1      //不分页
        },
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#menu-select');
                $select.append('<option value="">请选择</option>')
                for (var i in listData) {
                    if (listData[i].id != Swear.formValue.id) {
                        $select.append('<option value="' + listData[i].id + '">' + listData[i].name + '</option>')
                    }
                }
                form.val('LAYF-menu-form-edit', {
                    pid: Swear.formValue.pid,
                });
                form.render('select', 'LAYF-menu-form-edit'); //刷新select选择框
            }
        }
    });

})