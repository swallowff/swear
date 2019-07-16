layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form','set','upload'], function () {
    var $ = layui.$,
        form = layui.form,
        setter = layui.setter,
        layer = layui.layer,
        upload = layui.upload,
        layp = (layui.laytpl, layui.setter, layui.view, layui.admin);
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    form.val('LAYF-imageGallery-form-edit', {
        id: Swear.formValue.id,
        originName: Swear.formValue.originName,
        imgName: Swear.formValue.imgName,
        imgUrl: Swear.formValue.imgUrl,
        thumbnail: Swear.formValue.thumbnail,
        imgFormat: Swear.formValue.imgFormat,
        size: Swear.formValue.size,
    })

    //监听提交
    form.on('submit(LAYF-imageGallery-form-edit-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/sys/imageGallery/edit.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg,{
                        icon: 1,
                        time: 2000
                    });
                    parent.layui.table.reload('imageGalleryTable'); //重载表格
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg,{
                        icon: 5,
                        time: 2000
                    });
                }

            }
        });

    });

    var $imageSrc = $('#image-src')
    var $imageThumbnail = $('#image-thumbnail')
    upload.render({
        url: setter.ctxPath + "/sys/imageGallery/upload", elem: "#btn-image-upload", done: function (res) {
            if (res.code == setter.response.statusCode.ok) {
                layer.msg("上传成功",{
                    icon: 1,
                    time: 1200
                })
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
            }else {
                layer.msg(res.msg,{
                    icon: 5
                })
            }
        }
    }), layp.events.imagePreview = function () {
        var src = $imageSrc.val();
        layer.photos({photos: {title: "查看图片", data: [{src: src}]}, shade: .01, closeBtn: 1, anim: 5})
    }
})