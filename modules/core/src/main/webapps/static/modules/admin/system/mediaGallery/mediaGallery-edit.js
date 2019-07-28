layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form','upload'], function () {
    var $ = layui.$,
        form = layui.form,
        setter = layui.setter,
        layer = layui.layer,
        upload = layui.upload;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    form.val('LAYF-mediaGallery-form-edit', {
        id: Swear.formValue.id,
        mediaType: Swear.formValue.mediaType,
        originName: Swear.formValue.originName,
        name: Swear.formValue.name,
        localPath: Swear.formValue.localPath,
        url: Swear.formValue.url,
        cover: Swear.formValue.cover,
        mediaFormat: Swear.formValue.mediaFormat,
        size: Swear.formValue.size,
    })

    //监听提交
    form.on('submit(LAYF-mediaGallery-form-edit-submit)', function (data) {
        var field = data.field; //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/sys/mediaGallery/edit.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg,{
                        icon: 1,
                        time: 2000
                    });
                    parent.layui.table.reload('mediaGalleryTable'); //重载表格
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

    //上传视频
    var uploadInst = upload.render({
        elem: '#btn-video-upload'
        ,url: Swear.ctxPath + '/sys/mediaGallery/uploadVideo'
        ,field:"file"
        ,data:{"dir":"media"}
        ,accept: 'video' //视频
        // ,before:function (obj) {
        //     $('#demo9').css('display','block').attr('src', "http://p6nngxvb7.bkt.clouddn.com/FsyjSltTtkVtzepa_w7zsnS_S7zO"); //链接（base64）http://p6nngxvb7.bkt.clouddn.com/FsyjSltTtkVtzepa_w7zsnS_S7zO
        // }
        ,done: function(res){
            if(res.code == 0){
                let data = res.data;
                form.val('LAYF-mediaGallery-form-add',{
                    url: data.url,
                    mediaType: 0,
                    originName: data.originName,
                    name: data.name,
                    localPath: data.localPath,
                    mediaFormat: data.format,
                    size: data.size
                });
                layer.msg("上传成功",{icon: 1});
            }else {
                layer.msg(res.message,5);
            }
        }
        ,error:function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    })

    window.openVideo = function() {
        var videoUrl = document.getElementById("media-url").value;
        var index = layer.open({
            type: 2,
            content: videoUrl,
            area: ['600px', '450px'],
            offset:'t',
            maxmin: true,
            end: function () {

            }
        });
    }

    //上传音频
    var uploadAudio = upload.render({
        elem: '#btn-audio-upload'
        ,url: Swear.ctxPath + '/sys/mediaGallery/uploadAudio'
        ,field:"file"
        ,data:{}
        ,accept: 'audio' //视频
        // ,before:function (obj) {
        //     $('#demo9').css('display','block').attr('src', "http://p6nngxvb7.bkt.clouddn.com/FsyjSltTtkVtzepa_w7zsnS_S7zO"); //链接（base64）http://p6nngxvb7.bkt.clouddn.com/FsyjSltTtkVtzepa_w7zsnS_S7zO
        // }
        ,done: function(res){
            if(res.code == 0){
                let data = res.data;
                form.val('LAYF-mediaGallery-form-add',{
                    url: data.url,
                    mediaType: 1,
                    originName: data.originName,
                    name: data.name,
                    localPath: data.localPath,
                    mediaFormat: data.format,
                    size: data.size
                });
                layer.msg("上传成功",{icon: 1});
            }else {
                layer.msg(res.message,5);
            }
        }
        ,error:function () {
            layer.msg('服务器错误')
        }
    })

    window.openMedia = function() {
        var mediaType = $('#media-type').val();
        console.log(mediaType)
        var mediaUrl = document.getElementById("media-url").value;
        if (mediaType == 1) {
            var index = layer.open({
                type: 2,
                content: mediaUrl,
                area: ['400px', '300px'],
                offset:'t',
                maxmin: true,
                end: function () {

                }
            });
        }else if (mediaType == 0) {
            var index = layer.open({
                type: 2,
                content: mediaUrl,
                area: ['600px', '450px'],
                offset:'t',
                maxmin: true,
                end: function () {

                }
            });
        }else {
            layer.msg('不支持的媒体类型')
        }

    }
})