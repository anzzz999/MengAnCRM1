<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/2/29
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理</title>
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
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户名称：</label>
            <div class="layui-input-inline" >
                <input type="text" name="name"  autocomplete="off" class="layui-input" placeholder="请输入客户公司名称">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">区域查询：</label>
            <div class="layui-input-inline" >
                <input type="text" name="area"  autocomplete="off" class="layui-input" placeholder="请输入客户公司区域" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态查询：</label>
            <div class="layui-input-inline" >
                <select name="status"  >
                    <option value=""></option>
                    <option value="1">已签约</option>
                    <option value="2">跟进</option>
                    <option value="3">终止合同</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">等级查询：</label>
            <div class="layui-input-inline" >
                <select name="level" >
                    <option value=""></option>
                    <option value="1">☆</option>
                    <option value="2">☆☆☆</option>
                    <option value="3">☆☆☆☆☆</option>
                </select>
            </div>
        </div>
<%--        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>--%>
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
    <table class="layui-hide" id="customerTable" lay-filter="customerTable"></table>
    <div style="display: none;" id="menuToolBar">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>

         <%-- <button type="button" class="layui-btn layui-btn-sm" id="fileUpload" lay-event="fileUpload"><i class="layui-icon">&#xe67c;</i>上传合同</button>--%>
    </div>
    <div  id="menuBar" style="display: none;">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      <%--  <a class="layui-btn layui-btn-warm layui-btn-xs" name="fileUpload" lay-event="fileUpload" ><i class="layui-icon">&#xe67c;</i>上传合同</a>--%>
    </div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>公司名称：</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="text" name="name"  placeholder="请输入公司名称" autocomplete="off" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <%--<div class="layui-form-item">
            <label class="layui-form-label">公司名称：</label>
            <div class="layui-input-block">
                <div class="layui-unselect layui-form-select" id="pid_div">
                    <div class="layui-select-title">
                        <input type="hidden" name="id">
                        <input type="text" name="name"  placeholder="请输入公司名称" autocomplete="off" lay-verify="required"
                               class="layui-input">
                        <i class="layui-edge"></i>
                    </div>
                </div>
                &lt;%&ndash;				      <div class="layui-card select-test" id="menuSelectDiv">
                                        <div class="layui-card-body">
                                          <div id="toolbarDiv"><ul id="menuTree" class="dtree" data-id="0" style="width: 100%;"></ul></div>
                                        </div>
                                      </div>&ndash;%&gt;
            </div>
        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>区域:</label>
            <div class="layui-input-block">
                <input type="text" name="area"  placeholder="请输入区域名称" autocomplete="off" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>行业:</label>
            <div class="layui-input-block">
                <input type="text" name="industry" placeholder="请输入公司行业" autocomplete="off" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">* </span>状态:</label>
                <div class="layui-input-inline">
                    <%--<input type="text" name="status"   placeholder="请输入菜单图标"  autocomplete="off"
                           class="layui-input">--%>
                        <select name="status"  lay-verify="required">
                            <option value=""></option>
                            <option value="1">已签约</option>
                            <option value="2">跟进</option>
                            <option value="3">终止合同</option>
                        </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">* </span>等级:</label>
                <div class="layui-input-inline">
                        <select name="level" lay-verify="required">
                            <option value=""></option>
                            <option value="1">☆</option>
                            <option value="2">☆☆☆</option>
                            <option value="3">☆☆☆☆☆</option>
                        </select>
                                    </div>
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
    layui.use([ 'jquery', 'layer', 'form', 'table','upload'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;

       //渲染上传


        //渲染数据表格
        tableIns=table.render({
            elem: '#customerTable'   //渲染的目标对象
            ,url:'${ctx}/customer/loadAllCustomer.action' //数据接口
            ,title: '客户数据表'//数据导出来的标题
            ,toolbar:"#menuToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据

                {field:'id', title:'ID',align:'center',width:80}
                ,{field:'name', title:'公司名字',align:'center',width:180}
                ,{field:'area', title:'区域地址',align:'center'}
                ,{field:'industry', title:'行业',align:'center'}
                ,{field:'status', title:'状态',align:'center',templet:function(d){
                        return d.status=='1'?'已签约':d.status=='2'?'跟进':'终止合同';
                    }}
                ,{field:'level', title:'等级',align:'center',templet:function(d){
                        return d.level=='1'?'☆':d.level=='2'?'☆☆☆':'☆☆☆☆☆';
                    }}
                ,{field:'createtime', title:'创建时间',align:'center',width:180}
                ,{field:'userRealname', title:'创建人',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#menuBar', width:200,align:'center'}
            ]]
        })

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            /*alert(params);*/
            tableIns.reload({
                url:"${ctx}/customer/loadAllCustomer.action?"+params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(customerTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddCustomer();
                    break;
            };
        })

        //监听行工具事件
        table.on('tool(customerTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.name+'】这个客户吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/customer/deleteCustomer.action",{id:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateCustomer(data);
            }/*else if(layEvent === 'fileUpload'){ //编辑
                uploadInst.reload();
            }*/

        });


        var url;
        var mainIndex;
        //打开添加页面
        function openAddCustomer(){
            mainIndex=layer.open({
                type:1,
                title:'添加菜单',
                content:$("#saveOrUpdateDiv"),
                area:['800px','350px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();

                    /*//查询节点
                    $.post("${ctx}/menu/selectPidForAdd.action",function(data) {
                        /!* alert(data);*!/
                        $.each(data,function(index,element){
                            $("#pid").append("<option value="+element.id+">"+element.title+"</option>")
                        }) ;
                        form.render('select');
                    });*/
                    url="${ctx}/customer/addCustomer.action";
                }
            });
        }

        //打开修改页面
        function openUpdateCustomer(data){
            mainIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#saveOrUpdateDiv"),
                area:['800px','350px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${ctx}/customer/updateCustomer.action";
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
