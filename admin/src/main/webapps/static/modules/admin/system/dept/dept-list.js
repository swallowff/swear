layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    treeTable: 'tree/treeTable',
    treetable: 'treetable-lay/treetable'
}).use(['index', 'table', 'jquery', 'admin', 'treeTable', 'treetable'], function () {
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        admin = layui.admin,
        setter = layui.setter,
        treeTable = layui.treeTable,
        treetable = layui.treetable;

    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    // table.render({
    //     id: "deptTable", //table id
    //     elem: '#LAYF-list-table', //指定表格元素
    //     url: setter.ctxPath + '/sys/dept/list.ajax',  //请求路径
    //     cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    //     , skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
    //     , even: true    //隔行换色
    //     , page: true  //开启分页
    //     , limits: [10, 20, 50]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
    //     , limit: 10 //每页默认显示的数量
    //     , method: 'post'  //提交方式
    //     , cols: [[
    //         {type: 'checkbox'}, //开启多选框
    //         {type: 'numbers', title: '序号'}, //序号
    //         {
    //             field: 'fullName',
    //             title: '部门全称',
    //             sort: true
    //         },
    //         {
    //             field: 'pid',
    //             title: '父级ID',
    //             sort: true
    //         },
    //         {
    //             field: 'sort',
    //             title: '排序',
    //             sort: true
    //         },
    //         {
    //             field: 'createTime',
    //             title: '创建时间',
    //             sort: true
    //         },
    //         {
    //             title: '操作',
    //             templet: operation,
    //             width: 120,
    //             align: 'center'
    //         }
    //     ]]
    // });

    window.renderTable = function () {
        return treeTable.render({
            elem: '#LAYF-list-table',
            url: setter.ctxPath + '/sys/dept/treeTable.ajax',
            // where: field,
            icon_key: 'fullName',
            is_checkbox: true,
            primary_key: 'id',
            parent_key: 'pid',
            top_value: 0,
            hide_class: 'layui-hide',
            is_click_icon: false,
            icon: {
                open: 'layui-icon layui-icon-triangle-d',
                close: 'layui-icon layui-icon-triangle-r',
                left: 16,
            },
            // checked: {
            //     key: 'id',
            //     data: [0,1,4,10,11,5,2,6,7,3,8,9],
            // },
            end: function (e) {
                // console.log(e.data)
                // form.render();
            },
            cols: [
                {
                    key: 'fullName',
                    title: '名称',
                    width: '200px',
                    align: 'left'
                },
                {
                    key: 'pid',
                    title: '父ID',
                    width: '300px',
                    align: 'center'
                },
                {
                    key: 'pids',
                    title: '所有父ID',
                    align: 'center'
                },
                {
                    key: 'sort',
                    title: '排序',
                    width: '50px',
                    align: 'center'
                },
                {
                    key: 'createTime',
                    title: '创建时间',
                    width: '140px',
                    align: 'center'
                },
                {
                    title: '操作',
                    align: 'center',
                    width: '120px',
                    template: operation
                }
            ]
        });
    }

    var re = renderTable();

    //监听搜索
    form.on('submit(LAYF-list-search)', function (data) {
        //执行重载
        re = renderTable()
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkedIds = treeTable.checked(re)
            // console.log(checkedIds)
            if (checkedIds.length === 0) {
                return layer.msg('请选择数据')
            }

            layer.confirm('确定删除吗？', {
                btn: ['删除', '容朕想想'] //按钮
            }, function (index) {
                //执行 Ajax 后重载
                admin.req({
                    url: setter.ctxPath + '/sys/dept/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: checkedIds
                    },
                    success: function (res) {
                        renderTable();
                        layer.msg('已删除', {
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
                , title: '添加字典'
                , content: setter.ctxPath + '/sys/dept/add.html'
                , maxmin: true
                , area: ['550px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAYF-dept-form-add-submit");
                    submit.click();
                    renderTable();
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
        // console.log(re.childs[id])
        layer.confirm('将删除该节点及其所有下属节点,确定要删除吗?', {
            btn: ['删除', '容朕想想'] //按钮
        }, function () {
            //确定删除
            $.ajax({
                url: setter.ctxPath + '/sys/dept/deleteTree',
                method: 'POST',
                contentType: 'application/x-www-form-urlencoded',
                data: {
                    id: id
                },
                success: function (res) {
                    if (res.code == 0) {
                        layer.msg('成功删除' + res.data + '条数据');
                        renderTable();
                    } else {
                        layer.msg(res.msg)
                    }
                }
            })
        }, function () {

        });

    }

    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯部门'
            , content: setter.ctxPath + '/sys/dept/edit.html?id=' + id
            , maxmin: true
            , area: ['550px', '550px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#LAYF-dept-form-edit-submit");
                submit.click();
            }
        });
    };


});