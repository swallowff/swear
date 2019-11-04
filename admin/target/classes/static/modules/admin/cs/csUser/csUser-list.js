layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'jquery', 'admin'], function () {
    var table = layui.table, form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    table.render({
        id: "csUserTable",
        elem: '#LAYF-list-table',
        url: setter.ctxPath + '/cs/csUser/list.ajax',
        cellMinWidth: 20
        , skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
        , even: true    //隔行换色
        , page: true    //开启分页
        , limits: [10, 20, 50]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
        , limit: 10     //每页默认显示的数量
        , method: 'post'  //提交方式
        , cols: [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号'},
            {
                field: 'sysUid',
                title: '系统用户id',
                sort: true
            },
            {
                field: 'avatar',
                title: '头像',
                sort: true
            },
            {
                field: 'userName',
                title: '登录用户名',
                sort: true
            },
            {
                field: 'nickName',
                title: '昵称',
                sort: true
            },
            {
                field: 'password',
                title: '登录密码',
                sort: true
            },
            {
                field: 'telephone',
                title: '手机号',
                sort: true
            },
            {
                field: 'email',
                title: '邮箱',
                sort: true
            },
            {
                field: 'sign',
                title: '签名',
                sort: true
            },
            {
                field: 'isOnline',
                title: '是否在线',
                sort: true
            },
            {
                field: 'lastLoginIp',
                title: '上次登录ip',
                sort: true
            },
            {
                field: 'status',
                title: '用户状态: 启用 停用',
                sort: true
            },
            {
                field: 'createTime',
                title: '创建时间',
                sort: true
            },
            {
                title: '操作',
                templet: operation,
                width: 120,
                align: 'center'
            }
        ]]
    });

    //监听搜索
    form.on('submit(LAYF-csUser-list-search)', function (data) {
        var field = data.field;
        table.reload('csUserTable', {
            where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('csUserTable')
                , checkData = checkStatus.data; //得到选中的数据
            console.log(checkData)
            if (checkData.length === 0) {
                return layer.msg('请选择数据',{
                    icon: 5,
                    time: 1800
                });
            }

            var idAry = new Array();
            for (var i = 0; i < checkData.length; i++) {
                idAry[i] = checkData[i].id
            }

            layer.confirm('确定删除吗？', function (index) {

                //执行 Ajax 后重载
                admin.req({
                    url: setter.ctxPath + '/cs/csUser/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        table.reload('csUserTable');
                        layer.msg('已删除',{
                            icon: 1,
                            time: 1800
                        });
                    }

                });

            });
        },
        add: function () {
            layer.open({
                type: 2
                , title: '添加聊天系统用户'
                , content: setter.ctxPath + '/cs/csUser/add.html'
                , maxmin: true
                , area: ['550px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var submit = layero.find('iframe').contents().find("#LAYF-csUser-form-add-submit");
                    submit.click();
                }
            });
        }
    };

    $('.layui-btn.layuiadmin-btn-list').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function operation(data) {
        var id = data.id;
        var btn = $('#table-content-list-operation').html()
        btn = btn.replace(new RegExp('replacement','gm'),id)
        return btn
    }

    window.deleteRow = function (id) {
        $.ajax({
            url: setter.ctxPath + '/cs/csUser/delete',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {
                id: id
            },
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    layer.msg(res.msg,{
                        icon: 1,
                        time: 1800
                    });

                    layui.table.reload('csUserTable');
                } else {
                    layer.msg(res.msg,{
                        icon: 5,
                        time: 1800
                    });
                }
            }
        })
    }

    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯字典'
            , content: setter.ctxPath + '/cs/csUser/edit.html?id=' + id
            , maxmin: true
            , area: ['550px', '550px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                var submit = layero.find('iframe').contents().find("#LAYF-csUser-form-edit-submit");
                submit.click();
            }
        });
    };


});