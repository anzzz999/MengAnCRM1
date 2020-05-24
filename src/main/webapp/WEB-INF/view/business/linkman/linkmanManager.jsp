<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/3/1
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>联系人管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body  class="childrenBody">


<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名查询：</label>
            <div class="layui-input-inline" >
                <input type="text" name="name"  autocomplete="off" class="layui-input" placeholder="请输入客户联系人名称">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话查询：</label>
            <div class="layui-input-inline" >
                <input type="text" name="phone"  autocomplete="off" class="layui-input" placeholder="请输入客户联系人电话" >
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">性别查询：</label>
            <div class="layui-input-inline" >
                <select name="sex"  >
                    <option value=""></option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">公司名称：</label>
            <div class="layui-input-inline" >
                <input type="text" name="companyName"  autocomplete="off" class="layui-input" placeholder="请输入联系人公司名称" >
            </div>
        </div>

    </div>
    <div  class="layui-form-item" style="text-align: center;">
        <div class="layui-input-block" >
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="linkmanTable" lay-filter="linkmanTable"></table>
<div style="display: none;" id="menuToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <%--  <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>--%>
</div>
<div  id="menuBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>姓名：</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="text" name="name"  placeholder="请输入公司联系人姓名" autocomplete="off" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">* </span>性别:</label>
                <div class="layui-input-inline">
                    <select name="sex"  >
                        <option value=""></option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">* </span>生日:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="birthday" id="birthday" placeholder="输入格式为 yyyy-MM-dd" lay-verify="required">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>电话:</label>
            <div class="layui-input-block">
                <input type="text" name="phone"  placeholder="请输入联系人电话" autocomplete="off" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注:</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入联系人备注" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>客户公司:</label>
            <div class="layui-input-block ">

                    <select name="cid" id="cid" lay-verify="required">
                        <option value=""></option>

                    </select>
            </div>
        </div>

        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->


<script src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">


    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate =layui.laydate;

        $(function(){
            //为 新建选择弹出层 查询所有公司
            $.post("${ctx}/linkman/queryCustomerForFrom.action",function(data) {

                $.each(data,function(index,element){
                    $("#cid").append("<option value="+element.id+">"+element.name+"</option>")
                }) ;
            });
        });

        //渲染生日
        laydate.render({
            elem: '#birthday'
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#linkmanTable'   //渲染的目标对象
            ,url:'${ctx}/linkman/loadAllLinkman.action' //数据接口
            ,title: '联系人数据表'//数据导出来的标题
            ,toolbar:"#menuToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据

                {field:'id', title:'ID',align:'center'}
                ,{field:'name', title:'联系人姓名',align:'center',width:180}
                ,{field:'sex', title:'性别',align:'center',templet:function(d){
                        return d.sex=='1'?'<font color=blue>男</font>':'<font color=red>女</font>';
                    }}
                ,{field:'birthday', title:'生日',align:'center'}
                ,{field:'phone', title:'电话',align:'center'}
                ,{field:'remark', title:'备注',align:'center',width:180}
                ,{field:'companyName', title:'公司名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#menuBar', width:150,align:'center'}
            ]]
        })

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            /*alert(params);*/
            tableIns.reload({
                url:"${ctx}/linkman/loadAllLinkman.action?"+params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(linkmanTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddLinkman();
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(linkmanTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.name+'】这个联系人吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/linkman/deleteLinkman.action",{id:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateLinkman(data);
            }
        });

        var url;
        var mainIndex;


        //打开添加页面
        function openAddLinkman(){
            mainIndex=layer.open({
                type:1,
                title:'添加联系人',
                content:$("#saveOrUpdateDiv"),
                area:['800px','500px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();

/*                    //查询所有公司
                    $.post("${ctx}/linkman/queryCustomerForFrom.action",function(data) {

                        $.each(data,function(index,element){
                            $("#cid").append("<option value="+element.id+">"+element.name+"</option>")
                        }) ;
                        form.render('select');
                    });*/

                    url="${ctx}/linkman/addLinkman.action";
                }
            });
        }

        //打开修改页面
        function openUpdateLinkman(data){
            mainIndex=layer.open({
                type:1,
                title:'修改联系人',
                content:$("#saveOrUpdateDiv"),
                area:['800px','500px'],
                success:function(index){

                   /* //查询所有公司
                    $.post("${ctx}/linkman/queryCustomerForFrom.action",function(data) {
                        /!* alert(data);*!/
                        $.each(data,function(index,element){
                            $("#cid").append("<option value="+element.id+">"+element.name+"</option>")
                        }) ;
                        form.render('select');
                    });*/

                    form.val("dataFrm",data);
                    url="${ctx}/linkman/updateLinkman.action";
                }
            });
        }

        //保存
        form.on("submit(doSubmit)",function(obj){
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            $.post(url,params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

    });
</script>
</body>
</html>
