layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'jquery', 'admin'], function () {
    var table = layui.table, form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    table.render({
        id: "menuTable", //table id
        elem: '#LAYF-list-table', //指定表格元素
        url: setter.ctxPath + '/menu/list.ajax',  //请求路径
        cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
        , even: true    //隔行换色
        , page: true  //开启分页
        , limits: [10, 20, 50]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
        , limit: 10 //每页默认显示的数量
        , method: 'post'  //提交方式
        , cols: [[
            {type: 'checkbox'}, //开启多选框
            {type: 'numbers', title: '序号'}, //序号
            {
                field: 'name',
                title: '菜单名称',
                sort: true
            },
            {
                field: 'code',
                title: '权限CODE',
                sort: true
            },
            {
                field: 'pid',
                title: '父级菜单ID',
                sort: true
            },
            {
                field: 'icon',
                title: '图标',
                sort: false
            },
            {
                field: 'url',
                title: '跳转链接',
                sort: false
            },
            {
                field: 'sort',
                title: '排序',
                sort: true
            },
            {
                field: 'levels',
                title: '层级',
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
    form.on('submit(LAYF-menu-list-search-submit)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('menuTable', {
            where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('menuTable')
                , checkData = checkStatus.data; //得到选中的数据
            console.log(checkData)
            if (checkData.length === 0) {
                return layer.msg('请选择数据', {
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
                    url: setter.ctxPath + '/menu/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        // console.log(res)
                        table.reload('menuTable');
                        layer.msg('已删除', {
                            icon: 1,
                            time: 2000
                        });
                    }

                });

            });
        },
        add: function () {
            layer.open({
                type: 2
                , title: '添加菜单'
                , content: setter.ctxPath + '/menu/add.html'
                , maxmin: true
                , area: ['550px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAYF-menu-form-add-submit");
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
        btn = btn.replace(new RegExp('replacement', 'gm'), id)
        return btn
    }

    window.deleteRow = function (id) {
        $.ajax({
            url: setter.ctxPath + '/menu/delete',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {
                id: id
            },
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    layer.msg(res.msg, {
                        icon: 1,
                        time: 1800
                    });
                    layui.table.reload('menuTable');
                } else {
                    layer.msg(res.msg, {
                        icon: 5,
                        time: 1800
                    });
                }
            }
        })
    };

    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯菜单'
            , content: setter.ctxPath + '/menu/edit.html?id=' + id
            , maxmin: true
            , area: ['550px', '550px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#LAYF-menu-form-edit-submit");
                submit.click();
            }
        });
    };

    //渲染菜单下拉选择
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
                $select.append('<option value="">请选择父级菜单</option>');
                for (var i in listData) {
                    $select.append('<option value="' + listData[i].id + '">' + listData[i].name + '</option>')
                }
                form.render('select', 'LAYF-menu-list-search'); //刷新select选择框
            }
        }
    });

});