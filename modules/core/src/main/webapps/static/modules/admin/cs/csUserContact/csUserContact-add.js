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

    //渲染用户下拉选择
    $.ajax({
        url: setter.ctxPath + '/cs/csUser/list.ajax',
        data: {
            page: -1      //不分页
        },
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#uid-select');
                var $cuid = $('#cuid-select');
                $select.append('<option value="">请选择用户</option>');
                $cuid.append('<option value="">请选择联系人</option>');
                for (var i in listData) {
                    $select.append('<option value="' + listData[i].id + '">' + listData[i].nickName + '</option>')
                    $cuid.append('<option value="' + listData[i].id + '">' + listData[i].nickName + '</option>')
                }
                form.render('select', 'LAYF-csUserContact-form-add'); //刷新select选择框
            }
        }
    });

    //监听提交
    form.on('submit(LAYF-csUserContact-form-add-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/cs/csUserContact/add.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg,{
                        icon: 1,
                        time: 1800
                    });
                    parent.layui.table.reload('csUserContactTable'); //重载表格
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg,{
                        icon: 5,
                        time: 1800
                    });
                }
            }
        });

    });

    form.on('select(LAYF-uid-select)', function(data){
        $.ajax({
            url: Swear.ctxPath + '/cs/csUserContactGroup/list.ajax',
            method: 'post',
            data: {
                uid: data.value
            },
            success: function (res) {
                if (res.code === setter.response.statusCode.ok) {
                    let listData = res.data;
                    var $groupSelect = $('#contact-group-select');
                    $groupSelect.empty()
                    $groupSelect.append('<option value="">请选择分组</option>');
                    for (var i in listData) {
                        $groupSelect.append('<option value="' + listData[i].id + '">' + listData[i].groupName + '</option>')
                    }
                    form.render('select', 'LAYF-csUserContact-form-add'); //刷新select选择框
                }else {
                    layer.msg("请求分组数据有误")
                }

            }
        })
        // console.log(data.elem); //得到select原始DOM对象
        // console.log(data.value); //得到被选中的值
        // console.log(data.othis); //得到美化后的DOM对象
    });
})