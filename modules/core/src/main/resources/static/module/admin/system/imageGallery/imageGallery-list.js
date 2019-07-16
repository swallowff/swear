layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'jquery', 'admin'], function () {
    var table = layui.table, form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    table.render({
        id: "imageGalleryTable",
        elem: '#LAYF-list-table',
        url: setter.ctxPath + '/sys/imageGallery/list.ajax',
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
                field: 'originName',
                title: '原始名称',
                sort: true
            },
            {
                field: 'imgName',
                title: '图片名称',
                sort: true
            },
            {
                title: '图片预览',
                style:'height:100px;',
                templet: function (data) {
                    var src = data.thumbnail;
                    var img = '<img src="'+src+'" width="100px">'
                    return img;
                }
            },
            {
                field: 'imgUrl',
                title: '图片地址',
                sort: true
            },
            {
                field: 'thumbnail',
                title: '缩略图地址',
                sort: true
            },
            {
                field: 'imgFormat',
                title: '图片格式',
                sort: true
            },
            {
                field: 'size',
                title: '图片大小(字节)',
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
    form.on('submit(LAYF-imageGallery-list-search)', function (data) {
        var field = data.field;
        table.reload('imageGalleryTable', {
            where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('imageGalleryTable')
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
                    url: setter.ctxPath + '/sys/imageGallery/batchDel',
                    method: 'POST',
                    traditional: true,   //指定参数序列化时，不做深度序列化
                    data: {
                        ids: idAry
                    },
                    success: function (res) {
                        table.reload('imageGalleryTable');
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
                , title: '添加图片库'
                , content: setter.ctxPath + '/sys/imageGallery/add.html'
                , maxmin: true
                , area: ['800px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var submit = layero.find('iframe').contents().find("#LAYF-imageGallery-form-add-submit");
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
            url: setter.ctxPath + '/sys/imageGallery/delete',
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

                    layui.table.reload('imageGalleryTable');
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
            , title: '編輯图片库'
            , content: setter.ctxPath + '/sys/imageGallery/edit.html?id=' + id
            , maxmin: true
            , area: ['800px', '550px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                var submit = layero.find('iframe').contents().find("#LAYF-imageGallery-form-edit-submit");
                submit.click();
            }
        });
    };


});