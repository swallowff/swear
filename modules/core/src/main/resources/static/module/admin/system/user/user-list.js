layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    dtree: 'tree/dtree'
}).use(['index', 'contlist', 'table', 'jquery', 'admin', 'dtree'], function () {
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        admin = layui.admin,
        setter = layui.setter,
        dtree = layui.dtree;


    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    table.render({
        id: "userTable", //table id
        elem: '#LAYF-list-table', //指定表格元素
        url: setter.ctxPath + '/user/list.ajax',  //请求路径
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
                field: 'id', //json对应的key
                title: 'ID',   //列名
                sort: true  // 默认为 false,true为开启排序
            },
            {
                field: 'account',
                title: '账号',
                sort: true
            },
            {
                field: 'name',
                title: '用户名称',
                sort: true
            },
            {
                field: 'email',
                title: '邮箱',
                sort: true
            },
            {
                field: 'phone',
                title: '手机号',
                sort: true
            },
            {
                field: 'sex',
                title: '性别',
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
                width: 200,
                align: 'center'
            }
        ]]
    });

    //监听搜索
    form.on('submit(LAYF-user-list-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('userTable', {
            where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('userTable')
                , checkData = checkStatus.data; //得到选中的数据
            // console.log(checkData)
            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }

            var idAry = new Array();
            for (var i = 0; i < checkData.length; i++) {
                idAry[i] = checkData[i].id
            }

            layer.confirm('确定删除吗？', function (index) {
                //执行 Ajax 后重载
                admin.req({
                    url: setter.ctxPath + '/user/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        if (res.code == setter.response.statusCode.ok) {
                            layer.msg(res.msg, {
                                icon: 1,
                                time: 2000
                            });
                            table.reload('userTable');
                        } else {
                            layer.msg(res.msg, {
                                icon: 5,
                                time: 2000
                            });
                        }
                    }

                });

            });
        },
        add: function () {
            layer.open({
                type: 2
                , title: '添加用户'
                , content: setter.ctxPath + '/user/add.html'
                , maxmin: true
                , area: ['900px', '600px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAYF-user-form-add-submit");
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
            url: setter.ctxPath + '/user/delete',
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
                    layui.table.reload('userTable');
                } else {
                    layer.msg(res.msg, {
                        icon: 5,
                        time: 1800
                    });
                }
            }
        });
    };

    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯用户'
            , content: setter.ctxPath + '/user/edit.html?id=' + id
            , maxmin: true
            , area: ['800px', '600px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#LAYF-user-form-edit-submit");
                submit.click();
            }
        });
    };

    window.setupRole = function (id) {
        layer.open({
            type: 1,
            tittle: '选择角色',
            area: ['390px', '460px'],
            shade: 0.4,
            btn: ['确定', '取消'],
            // content: $('#role-select'),
            content: '<ul id="role-select" class="dtree" data-id="0" style="margin-left: 50px;margin-top: 20px;"></ul>',
            scrollbar: true,//屏蔽浏览器滚动条
            success: function (layero, index) {
                renderDtree(id);
            },
            yes: function (index) {
                $('#layui-layer' + index).find('.layui-layer-btn0').addClass('swear-a-disabled');   //防止重复提交
                var param = dtree.getCheckbarNodesParam("role-select")   //获取复选框选中数据
                console.log(param)
                var roleAry = new Array();
                for (var k = 0; k < param.length; k++) {
                    roleAry[k] = param[k].nodeId;
                }
                if (param.length === 0) {
                    $('#layui-layer' + index).find('.layui-layer-btn0').removeClass('swear-a-disabled');
                    return layer.msg('请选择菜单', {
                        icon: 5,
                        time: 2000
                    })
                }

                $.ajax({
                    url: setter.ctxPath + '/user/batchSetupRoles',
                    method: 'post',
                    traditional: true,
                    data: {
                        userId: id,
                        roleIds: roleAry
                    },
                    success: function (res) {
                        if (res.code === setter.response.statusCode.ok) {
                            layer.msg(res.msg, {
                                icon: 1,
                                time: 2000
                            });
                            layer.close(index);
                            table.reload('roleTable');
                        } else {
                            layer.msg(res.msg, {
                                icon: 5,
                                time: 2000
                            });
                        }
                    }
                });
                $('#layui-layer' + index).find('.layui-layer-btn0').removeClass('swear-a-disabled');
            }
        })
    }

    var renderDtree = function (userId) {
        dtree.render({
            elem: "#role-select",
            // icon: ["-1","5"],   //二级图标
            // ficon: ["-1","5"],   //一级图标
            url: setter.ctxPath + "/role/userDtree.ajax?userId=" + userId, // 使用url加载（可与data加载同时存在）
            // height: '150px',
            method: 'GET',
            dataStyle: "layuiStyle",  //使用layui风格的数据格式
            dataFormat: "list",  //配置data的风格为list
            accordion: false,  // 开启手风琴
            none: "提示说我没有数据？",
            line: true,  // 显示树线
            response: {message: "msg", statusCode: 0},  //修改response中返回数据的定义
            checkbar: true //开启复选框
        });
    }


});