layui.config({
    base: Swear.static + '/layuiadmin/', //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
}).use(['index','jquery', 'set', 'upload'], function () {
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




});