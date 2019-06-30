/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(function (e) {
    var i = (layui.$, layui.layer, layui.laytpl, layui.setter, layui.view, layui.admin);
    i.events.logout = function () {
        // alert(1)
        i.req({
            url: i.setter.ctxPath + "/login/logout", type: "get", data: {}, done: function (e) {
                i.exit(function () {
                    location.href = i.setter.ctxPath + "/login/login.html"
                })
            }
        })
    }, e("common", {})
});