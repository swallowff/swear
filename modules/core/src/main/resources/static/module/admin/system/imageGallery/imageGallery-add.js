layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form', 'set', 'upload'], function () {
    var $ = layui.$,
        form = layui.form,
        setter = layui.setter,
        layer = layui.layer,
        upload = layui.upload,
        layp = (layui.laytpl, layui.setter, layui.view, layui.admin);
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;


    var $imageSrc = $('#image-src')
    var $imageThumbnail = $('#image-thumbnail')
    upload.render({
        url: setter.ctxPath + "/cloud/imageGallery/upload", elem: "#btn-image-upload", done: function (res) {
            if (res.code == setter.response.statusCode.ok) {
                $imageSrc.val(res.data.src)
                if ($imageThumbnail) {
                    $imageThumbnail.val(res.data.thumbnail)
                }
                form.val('LAYF-imageGallery-form-add', {
                    originName: res.data.originName,
                    imgName: res.data.imgName,
                    imgUrl: res.data.src,
                    thumbnail: res.data.thumbnail,
                    imgFormat: res.data.imgFormat,
                    size: res.data.size,
                })
            } else {
                layer.msg(res.msg, {
                    icon: 5
                })
            }
        }
    }), layp.events.imagePreview = function () {
        var src = $imageSrc.val();
        layer.photos({photos: {title: "查看图片", data: [{src: src}]}, shade: .01, closeBtn: 1, anim: 5})
    }

    //监听提交
    form.on('submit(LAYF-imageGallery-form-add-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/cloud/imageGallery/add.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg, {
                        icon: 1,
                        time: 1800
                    });
                    parent.layui.table.reload('imageGalleryTable'); //重载表格
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
})