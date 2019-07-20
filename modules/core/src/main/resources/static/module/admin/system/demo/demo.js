layui.config({
    base: Swear.static + '/layuiadmin/', //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    eleTree: 'tree/eleTree',
    mod2: '{/}'+Swear.static+'/layuiadmin/lib/extend/echarts',    //{/}的意思即代表采用自有路径，即不跟随 base 路径
    dtree: 'tree/dtree',
    treeTable: 'tree/treeTable',
    treetable: 'treetable-lay/treetable'
}).use(['index', 'table', 'jquery', 'admin', 'set', 'upload', 'eleTree', 'echarts', 'dtree', 'treeTable', 'treetable'], function () {
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        admin = layui.admin,
        setter = layui.setter,
        upload = layui.upload,
        eleTree = layui.eleTree,
        dtree = layui.dtree,
        treetable = layui.treetable,
        treeTable = layui.treeTable;

    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    layui.data('test',{
        key:'name',
        value:'tom'
    })
    console.log(layui.data('test').name)

    //富文本编辑器
    var E = window.wangEditor
    var editor = new E('#contentEditor')
    editor.customConfig.uploadImgShowBase64 = true;
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
    editor.customConfig.uploadImgMaxLength = 5;
    editor.customConfig.zIndex = 100;
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.create();
    // 获取富文本的值 var editorText = editor.txt.html();

    //表格渲染
    table.render({
        id: "demoTable", //table id
        elem: '#LAYF-demo-list-table', //指定表格元素
        url: setter.ctxPath + '/demo/list.ajax',  //请求路径
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
                field: 'pid',
                title: '父ID',
                sort: true
            },
            {
                field: 'name',
                title: '部门名称',
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
                width: 120,
                align: 'center'
            }
        ]]
    });

    // treeTable
    var demoTreeTable = treeTable.render({
        elem: '#LAYF-demo-tree-table',
        // data: [{"id":1,"pid":0,"title":"1-1"},{"id":2,"pid":0,"title":"1-2"},{"id":3,"pid":0,"title":"1-3"},{"id":4,"pid":1,"title":"1-1-1"},{"id":5,"pid":1,"title":"1-1-2"},{"id":6,"pid":2,"title":"1-2-1"},{"id":7,"pid":2,"title":"1-2-3"},{"id":8,"pid":3,"title":"1-3-1"},{"id":9,"pid":3,"title":"1-3-2"},{"id":10,"pid":4,"title":"1-1-1-1"},{"id":11,"pid":4,"title":"1-1-1-2"}],
        url: setter.ctxPath + '/demo/treeTableList.ajax',
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
        checked: {
            key: 'id',
            data: [0, 1, 4, 10, 11, 5, 2, 6, 7, 3, 8, 9],
        },
        end: function (e) {
            console.log(e.data)
            form.render();
        },
        cols: [
            {
                key: 'fullName',
                title: '名称',
                width: '150px',
                align: 'center'
            },
            {
                key: 'pid',
                title: '父ID',
                width: '100px',
                align: 'center'
            },
            {
                key: 'sort',
                title: '排序',
                width: '100px',
                align: 'center'
            },
            {
                title: '操作',
                align: 'center',
                template: function (item) {
                    return '<a lay-filter="add">添加</a> | <a target="_blank" href="/detail?id=' + item.id + '">编辑</a>';
                }
            }
        ]
    });

    // 渲染laytable表格
    var renderTable = function () {//树桩表格参考文档：https://gitee.com/whvse/treetable-lay
        layer.load(2);
        treetable.render({
            treeColIndex: 1,//树形图标显示在第几列
            treeSpid: 0,//最上级的父级id
            treeIdName: 'id',//id字段的名称
            treePidName: 'pid',//pid字段的名称
            treeDefaultClose: false,//是否默认折叠
            treeLinkage: true,//父级展开时是否自动展开所有子级
            elem: '#permissionTable',
            url: setter.ctxPath + '/dept/laytreeTable.ajax',
            page: false,
            cols: [[
                {type: 'numbers', title: '编号'},
                {field: 'id', title: 'ID'},
                {field: 'name', title: '角色名称'},
                {field: 'sort', title: '排序'},
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    };

    renderTable();

    //触发三个button按钮
    $('#btn-expand').click(function () {
        laytable.expandAll('#permissionTable');
    });

    $('#btn-fold').click(function () {
        laytable.foldAll('#permissionTable');
    });

    $('#btn-refresh').click(function () {
        renderTable();
    });


    //监听搜索
    form.on('submit(LAYF-demo-list-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('userTable', {
            where: field
        });
    });

    var socket;

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('demoTable')
                , checkData = checkStatus.data; //得到选中的数据
            // console.log(checkData)
            if (checkData.length === setter.response.statusCode.ok) {
                return layer.msg('请选择数据');
            }

            var idAry = new Array();
            for (var i = 0; i < checkData.length; i++) {
                idAry[i] = checkData[i].id
            }

            layer.confirm('确定删除吗？', function (index) {
                //执行 Ajax 后重载
                admin.req({
                    url: setter.ctxPath + '/demo/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        // console.log(res)
                        table.reload('userTable');
                        layer.msg('已删除');
                    }

                });

            });
        },
        add: function () {
            layer.open({
                type: 2
                , title: '添加用户'
                , content: setter.ctxPath + '/demo/add.html'
                , maxmin: true
                , area: ['900px', '600px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAYF-demo-form-add-submit");
                    submit.click();
                }
            });
        },
        role: function () {
            layer.open({
                type: 1,
                tittle: '请选择角色',
                area: ['390px', '330px'],
                shade: 0.4,
                btn: ['确定', '取消'],
                content: $('#role-select'),
                scrollbar: true,//屏蔽浏览器滚动条
                yes: function (index) {//layer.msg('yes');    //点击确定回调
                    layer.close(index);
                    console.log('确定提交');
                }

            })

        },
        websocket: function () {
            if (socket && socket.readyState === WebSocket.OPEN){
                return layer.msg('已连接服务器',{
                    icon: 5
                });
            }
            socket = new WebSocket('ws://127.0.0.1:9999?userid=221341842032168960&subscription=orderPush');

            //监听open事件
            socket.onopen = () => {
                if (socket.readyState === WebSocket.OPEN){
                    layer.msg("连接服务器成功",{icon: 1})
                    // socket.send('hello')
                }
                // socket.binaryType = 'arraybuffer';
            }

            //监听messaeg事件
            socket.onmessage = e => {
                let data = e.data
                console.log(data)
            }

            socket.onerror = () => {
                layer.msg('connect error',{
                    icon: 5
                })
            }

            socket.onclose = e => {
                let clean = e.wasClean;
                let code = e.code;
                let reason = e.reason;
            }


        },
        sendTextMsg: function () {
            var content = $('#msgContent').val();
            if (!content){
                return layer.msg("请输入消息",{
                    icon: 5
                })
            }
            if (!socket || socket.readyState != WebSocket.OPEN){
                return layer.msg("未连接服务器",{icon: 5})
            }
            socket.binaryType = 'blob';
            // let arrayBuffer = new Uint8Array(strToBytes(content)).buffer;
            // var bytes = CommonUtil.stringToArrayBuffer(content)
            socket.send(content);
        },
        sendBinaryMsg: function () {
            var content = $('#msgContent').val();
            if (!content){
                return layer.msg("请输入消息",{icon: 5})
            }
            if (!socket || socket.readyState != WebSocket.OPEN){
                return layer.msg("未连接服务器",{icon: 5})
            }
            socket.binaryType = 'arraybuffer';
            var bytes = CommonUtil.stringToArrayBuffer(content)
            socket.send(bytes);
        },
        offLine: function () {
            if (!socket || socket.readyState != WebSocket.OPEN){
                return layer.msg("还没有连接服务器哟",{icon: 5})
            }
            socket.close();
            layer.msg("已断开连接",{icon: 1})
        }

    };

    //批量监听button 和上一个方法配合使用
    $('.layui-btn.layuiadmin-btn-list').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //动态修改表格行内元素   templet: operation,
    function operation(data) {
        var id = data.id;
        var btn = '';
        btn += '<button type="button" class="layui-btn layui-btn-xs" onclick="editRow(\'' + id + '\')"><i class="layui-icon">&#xe642;</i></button>'
        btn += '<button type="button" class="layui-btn layui-btn-xs" onclick="deleteRow(\'' + id + '\')"><i class="layui-icon">&#xe640;</i></button>'
        return btn;
    };

    //外部调用的方法必须使用window.funcName定义
    window.deleteRow = function (id) {
        $.ajax({
            url: setter.ctxPath + '/demo/delete',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {
                id: id
            },
            success: function (res) {
                layer.msg(res.msg);
                if (res.code == setter.response.statusCode.ok) {
                    layui.table.reload('demoTable');
                } else {

                }
            }
        });
    };

    //删除单行数据
    window.editRow = function (id) {
        layer.open({
            type: 2
            , title: '編輯用户'
            , content: setter.ctxPath + '/demo/edit.html?id=' + id
            , maxmin: true
            , area: ['800px', '600px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#LAYF-demo-form-edit-submit");
                submit.click();
            }
        });
    };

    //自定义普通图片上传
    var uploadInst = upload.render({
        elem: '#btn-demo-avatar-upload'
        , url: setter.ctxPath + '/upload/img'
        , type: 'POST'
        , before: function (obj) {
            layer.msg('加载中', {
                icon: 16
                , shade: 0.01
                , time: 20000
            });
            // layer.load(2);
            // setTimeout(function(){
            //     layer.closeAll('loading');
            // }, 5000);

            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo-avatar').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            layer.closeAll('tips');
            //如果上传失败
            if (res.code != setter.response.statusCode.ok) {
                return layer.msg('上传失败', {icon: 2, time: 3000});
            } else {
                //上传成功
                // console.log(res)
                layer.msg(res.msg, {
                    icon: 6,
                    time: 2300
                })
                $('#swear-user-avatar').val(res.data.src);
            }
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    // eleTree
    var eleTreeData = [{
        id: 1,
        label: "安徽省",
        children: [{
            id: 2,
            label: "马鞍山市",
            disabled: true,
            children: [{id: 3, label: "和县",}, {id: 4, label: "花山区",}]
        }]
    }, {id: 5, label: "河南省", children: [{id: 6, label: "郑州市"}]}];
    // eleTree   文档:https://fly.layui.com/extend/eleTree/#doc
    eleTree.render({
        elem: '.treeDemo1',
        // data: eleTreeData,
        emptText: '没有数据噢',
        url: setter.ctxPath + '/demo/eleTree.ajax',
        method: 'GET',
        showCheckbox: true,  //显示复选框
        highlightCurrent: true, //是否高亮当前选中节点，默认值是 false
        accordion: false,   //手风琴效果
        request: {      //对于后台数据重新定义名字
            key: "id",
            name: "fullName",
            children: "children",
            checked: "checked",
            disabled: "disabled",
            isLeaf: "isLeaf"
        },
        response: {     //对后台返回的数据格式重新定义
            statusName: "code",
            statusCode: 0,
            dataName: "data"
        },
        done: function (res) {
            // res即为你接口返回的信息。
            console.log(res);
        }
    });

    var dtreeData = [
        {"id": "001", "title": "湖南省", "checkArr": "0", "parentId": "0"},
        {"id": "002", "title": "湖北省", "checkArr": "0", "parentId": "0"},
        {"id": "003", "title": "广东省", "checkArr": "0", "parentId": "0"},
        {"id": "004", "title": "浙江省", "checkArr": "0", "parentId": "0"},
        {"id": "005", "title": "福建省", "checkArr": "0", "parentId": "0"},
        {"id": "001001", "title": "长沙市", "checkArr": "0", "parentId": "001"},
        {"id": "001002", "title": "株洲市", "checkArr": "0", "parentId": "001"},
        {"id": "001003", "title": "湘潭市", "checkArr": "0", "parentId": "001"},
        {"id": "001004", "title": "衡阳市", "checkArr": "0", "parentId": "001"},
        {
            "id": "001005",
            "title": "郴州市",
            "checkArr": "0",
            "iconClass": "dtree-icon-caidan_xunzhang",
            "parentId": "001"
        }
    ];
    //渲染dtree  文档:http://www.wisdomelon.com/DTreeHelper/
    dtree.render({
        elem: "#role-select",
        // data: dtreeData, // 使用data加载
        ficon: ["1", "5"],
        icon: ["1", "5"],
        url: setter.ctxPath + "/demo/dtree.ajax", // 使用url加载（可与data加载同时存在）
        method: 'GET',
        dataStyle: "layuiStyle",  //使用layui风格的数据格式
        dataFormat: "list",  //配置data的风格为list
        response: {message: "msg", statusCode: 0},  //修改response中返回数据的定义
        checkbar: true //开启复选框
    });

    // 绑定节点点击
    dtree.on("node('role-select')", function (obj) {
        layer.msg(JSON.stringify(obj.param));
    });


});