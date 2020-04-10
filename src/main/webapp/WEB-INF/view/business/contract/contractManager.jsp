<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/3/6
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>合同管理</title>
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
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">合同备注:</label>
            <div class="layui-input-inline">
                <input type="text" name="remark"  autocomplete="off" class="layui-input" placeholder="可模糊搜索">
            </div>
        </div>

        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="contractTable" lay-filter="contractTable"></table>
<div style="display: none;" id="newsToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
</div>
<div  id="newsBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="downloadContract">下载合同</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>客户公司:</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <select name="cid" id="cid"  lay-verify="required">
                    <option value=""></option>

                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>选择文件:</label>
            <div class="layui-input-block">
                <button type="button" class="layui-btn  layui-btn-sm" id="uploadBtn" >
                    <i class="layui-icon">&#xe67c;</i>上传合同
                </button>
                <input type="hidden" name="url" id="url" lay-verify="required">
                <%--<textarea placeholder="请输入内容"  name="content" lay-verify="content" id="content"  class="layui-textarea" style="height: 330px"></textarea>--%>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">合同备注:</label>
            <div class="layui-input-block">
                <input type="text" name="remark"  placeholder="请输入合同备注" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->

<script src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate','upload' ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;
        var upload = layui.upload;

        //上传图片
        //上传缩略图
        upload.render({
            elem: '#uploadBtn',
            url: '${ctx}/file/uploadFile.action',
            method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            accept:'file',
            field:"mf",
            done: function(res, index, upload){
/*                $('#showCarImg').attr('src',"${ctx}/file/downloadShowFile.action?path="+res.data.src);*/
                $('#url').val(res.data.src);
/*                $('#carimgDiv').css("background","#fff");*/
            }
        });


        $(function(){
            //为 新建选择弹出层 查询所有公司
            $.post("${ctx}/linkman/queryCustomerForFrom.action",function(data) {

                $.each(data,function(index,element){
                    $("#cid").append("<option value="+element.id+">"+element.name+"</option>")
                }) ;
            });
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#contractTable'   //渲染的目标对象
            ,url:'${ctx}/contract/loadAllContract.action' //数据接口
            ,title: '用户数据表'//数据导出来的标题
            ,toolbar:"#newsToolBar"   //表格的工具条
            ,height:'full-150'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {field:'id', title:'ID',align:'center'}
                ,{field:'cname', title:'客户公司',align:'center'}
                ,{field:'remark', title:'备注',align:'center'}
                ,{field:'url', title:'合同路径',align:'center'}
                ,{field:'createtime', title:'创建时间',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#newsBar', width:230,align:'center'}
            ]]
        })

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${ctx}/contract/loadAllContract.action?"+params,
                page:{curr:1}
            })
        });


        //监听头部工具栏事件
        table.on("toolbar(contractTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddContract();
                    break;
            };
        })

        //监听行工具事件
        table.on('tool(contractTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.cname+'】的这个合同吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/contract/deleteContract.action",{"id":data.id,"url":data.url},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateContract(data);
            }else if(layEvent==='downloadContract'){
                downloadContract(data);
            }
        });


        var url;
        var mainIndex;
        //打开添加页面
        function openAddContract(){
            mainIndex=layer.open({
                type:1,
                title:'添加记录',
                content:$("#saveOrUpdateDiv"),
                area:['800px','550px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    $("#uploadBtn").show();
                    url="${ctx}/contract/addContract.action";
                }
            });
        }

        //打开修改页面
        function openUpdateContract(data){
            mainIndex=layer.open({
                type:1,
                title:'修改',
                content:$("#saveOrUpdateDiv"),
                area:['800px','550px'],
                success:function(index){
                    /*editIndex=layedit.build('content'); //建立编辑器*/
                    form.val("dataFrm",data);
                    $("#uploadBtn").hide();
                    url="${ctx}/contract/updateContract.action";
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

        //合同下载
        function downloadContract(data){
            window.open("${ctx}/file/downloadFile.action?path="+data.url+"&&cname="+data.cname);
            /*$.post("${ctx}/file/downloadFile.action",{id:data.id},function(obj){
                /!*layer.msg(obj.msg);*!/
                return obj;

            })*/
        }


    });
</script>
</body>
</html>
