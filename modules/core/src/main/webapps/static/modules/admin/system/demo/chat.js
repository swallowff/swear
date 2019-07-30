layui.use('layim', function(){
    var layim = layui.layim
        ,socket   //websocket
        ,heartbeatInterval;  //心跳定时器

    //基础配置
    layim.config({
        //初始化接口
        init: {
            url: Swear.ctxPath + '/sys/chat/init'
            ,data: {
                csuid: Swear.csuid
            }
        }
        //查看群员接口
        ,members: {
            url: '/layim/json/getMembers.json'
            ,data: {}
        }

        ,uploadImage: {
            url: Swear.ctxPath + '/sys/imageGallery/upload' //（返回的数据格式见下文）
            ,type: 'post' //默认post
        }
        ,uploadFile: {
            url: '' //（返回的数据格式见下文）
            ,type: '' //默认post
        }

        ,isAudio: true //开启聊天工具栏音频
        ,isVideo: true //开启聊天工具栏视频

        //扩展工具栏
        ,tool: [{
            alias: 'code'
            ,title: '代码'
            ,icon: '&#xe64e;'
        }]

        ,brief: false //是否简约模式（若开启则不显示主面板）

        ,title: 'Chat' //自定义主面板最小化时的标题
        ,right: '100px' //主面板相对浏览器右侧距离
        //,minRight: '90px' //聊天面板最小化时相对浏览器右侧距离
        ,initSkin: '3.jpg' //1-5 设置初始背景
        //,skin: ['aaa.jpg'] //新增皮肤
        ,isfriend: true //是否开启好友
        ,isgroup: true //是否开启群组
        //,min: true //是否始终最小化主面板，默认false
        //,notice: true //是否开启桌面消息提醒，默认false
        //,voice: false //声音提醒，默认开启，声音文件为：default.mp3

        // ,msgbox: '/layim/demo/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
        // ,find: '/layim/demo/find.html' //发现页面地址，若不开启，剔除该项即可
        // ,chatLog: '/layim/demo/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可

    });
    //监听在线状态的切换事件
    layim.on('online', function(status){
        layer.msg(status);
    });

    //监听签名修改
    layim.on('sign', function(value){
        layer.msg(value);
    });
    //监听自定义工具栏点击，以添加代码为例
    layim.on('tool(code)', function(insert){
        layer.prompt({
            title: '插入代码'
            ,formType: 2
            ,shade: 0
        }, function(text, index){
            layer.close(index);
            insert('[pre class=layui-code]' + text + '[/pre]'); //将内容插入到编辑器
        });
    });

    //监听layim建立就绪
    layim.on('ready', function(res){
        //console.log(res.mine);
        layim.msgbox(5); //模拟消息盒子有新消息，实际使用时，一般是动态获得
    });
    //监听发送消息
    layim.on('sendMessage', function(data){
        var To = data.to;
        // console.log(data);

        // if(To.type === 'friend'){
        //     layim.setChatStatus('<span style="color:#FF5722;">对方正在输入。。。</span>');
        // }
        if (!socket || socket.readyState != WebSocket.OPEN) {
            return layer.msg('还没有连接服务器哟');
        }else {
            let msgPacket = {
                requestType: 'message',
                body: {
                    to: To.id,
                    type: 'text',
                    content: data.mine.content
                }
            }

            socket.send(JSON.stringify(msgPacket))
        }

    });

    //监听查看群员
    layim.on('members', function(data){
        //console.log(data);
    });

    //监听聊天窗口的切换
    layim.on('chatChange', function(res){
        var type = res.data.type;
        // console.log(res.data.id)
        if(type === 'friend'){
            //模拟标注好友状态
            //layim.setChatStatus('<span style="color:#FF5722;">在线</span>');
        } else if(type === 'group'){
            //模拟系统消息
            layim.getMessage({
                system: true
                ,id: res.data.id
                ,type: "group"
                ,content: '模拟群员'+(Math.random()*100|0) + '加入群聊'
            });
        }
    });


    //面板外的操作
    var $ = layui.jquery, active = {
        chat: function(){
            //自定义会话
            layim.chat({
                name: '小闲'
                ,type: 'friend'
                ,avatar: '//tva3.sinaimg.cn/crop.0.0.180.180.180/7f5f6861jw1e8qgp5bmzyj2050050aa8.jpg'
                ,id: '221341842032168960'
            });
            layer.msg('也就是说，此人可以不在好友面板里');
        }
        ,message: function(){
            //接收好友消息
            layim.getMessage({
                username: "贤心"
                ,avatar: "//tp1.sinaimg.cn/1571889140/180/40030060651/1"
                ,id: "100001"
                ,type: "friend"
                ,content: "嗨，你好！欢迎体验LayIM。演示标记："+ new Date().getTime()
                ,timestamp: new Date().getTime()
            });
        }
        ,messageAudio: function(){
            //接受音频消息
            layim.getMessage({
                username: "林心如"
                ,avatar: "//tp3.sinaimg.cn/1223762662/180/5741707953/0"
                ,id: "76543"
                ,type: "friend"
                ,content: "audio[http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201510/6473.mp3]"
                ,timestamp: new Date().getTime()
            });
        }
        ,messageVideo: function(){
            //接受视频消息
            layim.getMessage({
                username: "林心如"
                ,avatar: "//tp3.sinaimg.cn/1223762662/180/5741707953/0"
                ,id: "76543"
                ,type: "friend"
                ,content: "video[http://www.w3school.com.cn//i/movie.ogg]"
                ,timestamp: new Date().getTime()
            });
        }
        ,messageTemp: function(){
            //接受临时会话消息
            layim.getMessage({
                username: "小酱"
                ,avatar: "//tva1.sinaimg.cn/crop.7.0.736.736.50/bd986d61jw8f5x8bqtp00j20ku0kgabx.jpg"
                ,id: "198909151014"
                ,type: "friend"
                ,content: "临时："+ new Date().getTime()
            });
        }
        ,add: function(){
            //实际使用时数据由动态获得
            layim.add({
                type: 'friend'
                ,username: '麻花疼'
                ,avatar: 'http://admin.helloccs.cn:80/swear/upload/img/2019-07-16/4fa70ffeb53942d9b614378e5c488b07.jpeg.png'
                ,submit: function(group, remark, index){
                    layer.msg('好友申请已发送，请等待对方确认', {
                        icon: 1
                        ,shade: 0.5
                    }, function(){
                        layer.close(index);
                    });

                    //通知对方
                    /*
                    $.post('/im-applyFriend/', {
                      uid: info.uid
                      ,from_group: group
                      ,remark: remark
                    }, function(res){
                      if(res.status != 0){
                        return layer.msg(res.msg);
                      }
                      layer.msg('好友申请已发送，请等待对方确认', {
                        icon: 1
                        ,shade: 0.5
                      }, function(){
                        layer.close(index);
                      });
                    });
                    */
                }
            });
        }
        ,addqun: function(){
            layim.add({
                type: 'group'
                ,username: 'LayIM会员群'
                ,avatar: '//tva2.sinaimg.cn/crop.0.0.180.180.50/6ddfa27bjw1e8qgp5bmzyj2050050aa8.jpg'
                ,submit: function(group, remark, index){
                    layer.msg('申请已发送，请等待管理员确认', {
                        icon: 1
                        ,shade: 0.5
                    }, function(){
                        layer.close(index);
                    });

                    //通知对方
                    /*
                    $.post('/im-applyGroup/', {
                      uid: info.uid
                      ,from_group: group
                      ,remark: remark
                    }, function(res){

                    });
                    */
                }
            });
        }
        ,addFriend: function(){
            var user = {
                type: 'friend'
                ,id: 1234560
                ,username: '李彦宏' //好友昵称，若申请加群，参数为：groupname
                ,avatar: '//tva4.sinaimg.cn/crop.0.0.996.996.180/8b2b4e23jw8f14vkwwrmjj20ro0rpjsq.jpg' //头像
                ,sign: '全球最大的中文搜索引擎'
            }
            layim.setFriendGroup({
                type: user.type
                ,username: user.username
                ,avatar: user.avatar
                ,group: layim.cache().friend //获取好友列表数据
                ,submit: function(group, index){
                    //一般在此执行Ajax和WS，以通知对方已经同意申请
                    //……

                    //同意后，将好友追加到主面板
                    layim.addList({
                        type: user.type
                        ,username: user.username
                        ,avatar: user.avatar
                        ,groupid: group //所在的分组id
                        ,id: user.id //好友ID
                        ,sign: user.sign //好友签名
                    });

                    layer.close(index);
                }
            });
        }
        ,addGroup: function(){
            layer.msg('已成功把[Angular开发]添加到群组里', {
                icon: 1
            });
            //增加一个群组
            layim.addList({
                type: 'group'
                ,avatar: "//tva3.sinaimg.cn/crop.64.106.361.361.50/7181dbb3jw8evfbtem8edj20ci0dpq3a.jpg"
                ,groupname: 'Angular开发'
                ,id: "12333333"
                ,members: 0
            });
        }
        ,removeFriend: function(){
            layer.msg('已成功删除[凤姐]', {
                icon: 1
            });
            //删除一个好友
            layim.removeList({
                id: 121286
                ,type: 'friend'
            });
        }
        ,removeGroup: function(){
            layer.msg('已成功删除[前端群]', {
                icon: 1
            });
            //删除一个群组
            layim.removeList({
                id: 101
                ,type: 'group'
            });
        }
        //置灰离线好友
        ,setGray: function(){
            layim.setFriendStatus(168168, 'offline');

            layer.msg('已成功将好友[马小云]置灰', {
                icon: 1
            });
        }
        //取消好友置灰
        ,unGray: function(){
            layim.setFriendStatus(168168, 'online');

            layer.msg('成功取消好友[马小云]置灰状态', {
                icon: 1
            });
        }
        //移动端版本
        ,mobile: function(){
            var device = layui.device();
            var mobileHome = '/layim/demo/mobile.html';
            if(device.android || device.ios){
                return location.href = mobileHome;
            }
            var index = layer.open({
                type: 2
                ,title: '移动版演示 （或手机扫右侧二维码预览）'
                ,content: mobileHome
                ,area: ['375px', '667px']
                ,shadeClose: true
                ,shade: 0.8
                ,end: function(){
                    layer.close(index + 2);
                }
            });
            layer.photos({
                photos: {
                    "data": [{
                        "src": "http://cdn.layui.com/upload/2016_12/168_1481056358469_50288.png",
                    }]
                }
                ,anim: 0
                ,shade: false
                ,success: function(layero){
                    layero.css('margin-left', '350px');
                }
            });
        },
        connectServer: function () {
            openConnection();
        },
        closeConnect: function () {
            closeConnection()
        }
    };

    window.openConnection = function(){
        if (socket && socket.readyState === WebSocket.OPEN){
            return layer.msg('已连接服务器',{icon: 5});
        }
        socket = new WebSocket(Swear.websocketUrl+'?userid='+Swear.csuid);

        //监听open事件
        socket.onopen = () => {
            if (socket.readyState === WebSocket.OPEN){
                layer.msg("连接服务器成功",{icon: 1})
                //定时器发送心跳
                heartbeatInterval = window.setInterval(function () {
                    socket.send('Heartbeat')
                },1000*20)
            }
            // socket.binaryType = 'arraybuffer';   用于发送二进制数据  一般不使用
        }

        //监听messaeg事件
        socket.onmessage = e => {
            let data = e.data;
            let resJson = JSON.parse(data);
            // console.log(resJson)
            if (resJson.data) {
                layim.getMessage(resJson.data);
            }
        }

        //监听连接错误事件
        socket.onerror = () => {
            layer.msg('connect error',{
                icon: 5
            })
        }

        //监听服务器主动关闭事件
        socket.onclose = e => {
            if (heartbeatInterval) {
                window.clearInterval(heartbeatInterval)
            }
            let clean = e.wasClean;
            let code = e.code;
            let reason = e.reason;
        }
    }

    window.closeConnection = function(){
        if (heartbeatInterval) {
            window.clearInterval(heartbeatInterval)
        }
        if (socket){
            if (socket.readyState === WebSocket.OPEN){
                socket.close()
                layer.msg('成功断开连接',{icon:1})
            }else {
                layer.msg('未连接服务器')
            }
        }else {
            layer.msg('未连接服务器')
        }
    }

    openConnection();

    $('.site-demo-layim').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});