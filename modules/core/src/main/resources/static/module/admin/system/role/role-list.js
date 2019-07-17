layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    dtree: 'tree/dtree'
}).use(['index', 'table', 'jquery', 'admin', 'dtree'], function () {
    var table = layui.table, form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter,
        dtree = layui.dtree;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    table.render({
        id: "roleTable", //table id
        elem: '#LAYF-list-table', //指定表格元素
        url: setter.ctxPath + '/role/list.ajax',  //请求路径
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
                title: '角色名称',
                sort: true
            },
            {
                field: 'code',
                title: '角色码',
                sort: true
            },
            {
                field: 'deptId',
                title: '部门ID',
                sort: true
            },
            {
                field: 'pid',
                title: '父级角色',
                sort: true
            },
            {
                field: 'sort',
                title: '排序',
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
    form.on('submit(LAYF-role-list-search-submit)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('roleTable', {
            where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('roleTable')
                , checkData = checkStatus.data; //得到选中的数据
            console.log(checkData)
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
                    url: setter.ctxPath + '/role/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        // console.log(res)
                        table.reload('roleTable');
                        layer.msg('已删除');
                    }

                });

            });
        },
        add: function () {
            layer.open({
                type: 2
                , title: '添加角色'
                , content: setter.ctxPath + '/role/add.html'
                , maxmin: true
                , area: ['550px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAYF-role-form-add-submit");
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
            url: setter.ctxPath + '/role/delete',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {
                id: id
            },
            success: function (res) {
                layer.msg(res.msg);
                if (res.code == 0) {
                    layui.table.reload('roleTable');
                } else {

                }
            }
        })
    };

    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯角色'
            , content: setter.ctxPath + '/role/edit.html?id=' + id
            , maxmin: true
            , area: ['550px', '550px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#LAYF-role-form-edit-submit");
                submit.click();
            }
        });
    };

    window.setupAuth = function (id) {
        layer.open({
            type: 1,
            tittle: '选择菜单',
            area: ['390px', '460px'],
            shade: 0.4,
            btn: ['确定', '取消'],
            // content: $('#role-select'),
            content: '<ul id="auth-select" class="dtree" data-id="0" style="margin-left: 50px;margin-top: 20px;"></ul>',
            scrollbar: true,//屏蔽浏览器滚动条
            success: function (layero, index) {
                renderRoleDtree(id);
            },
            yes: function (index) {
                $('#layui-layer' + index).find('.layui-layer-btn0').addClass('swear-a-disabled');   //防止重复提交
                var param = dtree.getCheckbarNodesParam("auth-select")   //获取复选框选中数据
                // console.log(param)
                var menuIdAry = new Array();
                for (var k = 0; k < param.length; k++) {
                    menuIdAry[k] = param[k].nodeId;
                }
                if (param.length === 0) {
                    $('#layui-layer' + index).find('.layui-layer-btn0').removeClass('swear-a-disabled');
                    return layer.msg('请选择菜单', {
                        icon: 5,
                        time: 2000
                    })
                }

                $.ajax({
                    url: setter.ctxPath + '/role/batchSetupAuth',
                    method: 'post',
                    traditional: true,
                    data: {
                        roleId: id,
                        menuIds: menuIdAry
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

    var renderRoleDtree = function (roleId) {
        dtree.render({
            elem: "#auth-select",
            // icon: ["1","5"],   //二级图标
            // ficon: ["1","5"],   //一级图标
            url: setter.ctxPath + "/menu/roleDtree.ajax?roleId=" + roleId, // 使用url加载（可与data加载同时存在）
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
                $select.append('<option value="">请选择部门</option>');
                for (var i in listData) {
                    $select.append('<option value="' + listData[i].id + '">' + listData[i].fullName + '</option>')
                }
                form.render('select', 'LAYF-role-list-search'); //刷新select选择框
            }
        }
    });

});