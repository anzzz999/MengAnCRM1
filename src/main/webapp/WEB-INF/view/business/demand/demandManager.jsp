<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/4/7
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>需求处理</title>
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
            <label class="layui-form-label">需求标题:</label>
            <div class="layui-input-inline">
                <input type="text" name="title"  autocomplete="off" class="layui-input" placeholder="可模糊搜索">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">需求内容:</label>
            <div class="layui-input-inline">
                <input type="text" name="content"  autocomplete="off" class="layui-input" placeholder="可模糊搜索">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">需求分类:</label>
            <div class="layui-input-inline">
                <select name="category" >
                    <option value=""></option>
                    <option value="业务需求">业务需求</option>
                    <option value="bug处理">bug处理</option>
                    <option value="功能需求">功能需求</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">紧急状态:</label>
            <div class="layui-input-inline">
                <select name="level"  >
                    <option value=""></option>
                    <option value="⭐">⭐</option>
                    <option value="⭐⭐⭐">⭐⭐⭐</option>
                    <option value="⭐⭐⭐⭐⭐">⭐⭐⭐⭐⭐</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">处理状态:</label>
            <div class="layui-input-inline">
                <select name="status" >
                    <option value=""></option>
                    <option value="未处理">未处理</option>
                    <option value="已处理">已处理</option>
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
<table class="layui-hide" id="demandTable" lay-filter="demandTable"></table>
<div style="display: none;" id="demandToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div  id="demandBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="viewDemand">查看</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>客户公司:</label>
            <div class="layui-input-block ">

                <select name="cid" id="cid" lay-verify="required">
                    <option value=""></option>

                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>需求分类:</label>
            <div class="layui-input-block">
                <select name="category"  lay-verify="required">
                    <option value=""></option>
                    <option value="业务需求">业务需求</option>
                    <option value="bug处理">bug处理</option>
                    <option value="功能需求">功能需求</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>需求标题:</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="text" name="title"  placeholder="请输入需求标题" autocomplete="off"
                       class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>具体内容:</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容"  name="content" lay-verify="content" id="content"  class="layui-textarea" style="height: 200px" lay-verify="required"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>紧急程度:</label>
            <div class="layui-input-block">
                <select name="level"  lay-verify="required">
                    <option value=""></option>
                    <option value="⭐">⭐</option>
                    <option value="⭐⭐⭐">⭐⭐⭐</option>
                    <option value="⭐⭐⭐⭐⭐">⭐⭐⭐⭐⭐</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red;">* </span>处理状态:</label>
            <div class="layui-input-block">
                <select name="status"  lay-verify="required">
                    <option value="未处理">未处理</option>
                    <option value="已处理">已处理</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">处理结果:</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入处理结果"  name="result"  id="result"  class="layui-textarea" style="height: 200px"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">处理时间:</label>
            <div class="layui-input-block">
                <input type="text" name="endtime"  id="endtime" readonly="readonly" autocomplete="off"  class="layui-input">
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


<!-- 查看需求的div -->
<div id="viewDemandDiv" style="padding: 10px;display: none;">
    <br/>
    <h2 id="view_title" align="center"></h2>
    <hr/>
    <div style="text-align: right;">
        发布人:<span id="view_opername"></span>  <span style="display: inline-block;width: 20px" ></span>
        发布时间:<span id="view_createtime"></span>
    </div>
    <hr/>
    <h3 id="view_content" style="font-weight: bold"></h3>
    <br/>
    <hr/>
    <br/><br/>
    <div style="text-align: right;">
        <%--处理结果:<span id="view_status"></span>--%>
        <h2 id="view_status" align="center"></h2>
    </div>
    <hr/>
        <div style="text-align: right;">
        紧急程度:<span id="view_level"></span> <span style="display: inline-block;width: 20px" ></span>
        处理时间:<span id="view_endtime"></span>
        </div>
    <h3 id="view_result" style="font-weight: bold"></h3>
</div>

<script src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate' ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;

        $(function(){
            //为 新建选择弹出层 查询所有公司
            $.post("${ctx}/linkman/queryCustomerForFrom.action",function(data) {

                $.each(data,function(index,element){
                    $("#cid").append("<option value="+element.id+">"+element.name+"</option>")
                }) ;
            });
        });

        //渲染时间

        laydate.render({
            elem:'#endtime',
            type:'datetime'
        });
/*        //初始化富文本编辑器
        var editIndex;*/


        //渲染数据表格
        tableIns=table.render({
            elem: '#demandTable'   //渲染的目标对象
            ,url:'${ctx}/demand/loadAllDemand.action' //数据接口
            ,title: '用户数据表'//数据导出来的标题
            ,toolbar:"#demandToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID',align:'center'}
                ,{field:'companyName', title:'公司',align:'center'}
                ,{field:'category', title:'需求分类',align:'center'}
                ,{field:'title', title:'需求标题',align:'center'}
                 ,{field:'content', title:'需求内容',align:'center'}
                ,{field:'opername', title:'创建人',align:'center'}
                ,{field:'createtime', title:'发布时间',align:'center'}
                ,{field:'level', title:'紧急程度',align:'center'}
                ,{field:'status', title:'处理状态',align:'center',templet:function(d){
                        return d.status=='未处理'?'<font color=red>未处理</font>':'<font color=green>已处理</font>';
                        }}
                ,{field:'result', title:'处理结果',align:'center'}
                ,{field:'endtime', title:'处理时间',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#demandBar', width:220,align:'center'}
            ]]
        })
        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${ctx}/demand/loadAllDemand.action?"+params,
                page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(demandTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddDemand();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(demandTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.title+'】这个需求吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/demand/deleteDemand.action",{id:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateDemand(data);
            }else if(layEvent==='viewDemand'){
                viewDemand(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddDemand(){
            mainIndex=layer.open({
                type:1,
                title:'添加需求',
                content:$("#saveOrUpdateDiv"),
                area:['800px','550px'],
                success:function(index){
                    /*editIndex=layedit.build('content'); //建立编辑器*/
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${ctx}/demand/addDemand.action";
                }
            });
        }

/*        $("#dataFrmResetBtn").click(function(){
            layedit.setContent(editIndex,"");
        });*/
        //打开修改页面
        function openUpdateDemand(data){
            mainIndex=layer.open({
                type:1,
                title:'修改需求',
                content:$("#saveOrUpdateDiv"),
                area:['800px','550px'],
                success:function(index){
                    /*editIndex=layedit.build('content'); //建立编辑器*/
                    form.val("dataFrm",data);
                    url="${ctx}/demand/updateDemand.action";
                }
            });
        }
        //保存
        form.on("submit(doSubmit)",function(obj){
            /*layedit.sync(editIndex);//把富文本里面的数据同步到自己写的textarea里面*/
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

        //查看
        function viewDemand(data){
            mainIndex=layer.open({
                type:1,
                title:'查看需求',
                content:$("#viewDemandDiv"),
                area:['800px','550px'],
                success:function(index){
                    $("#view_title").html(data.title);
                    $("#view_opername").html(data.opername);
                    $("#view_createtime").html(data.createtime);
                    $("#view_content").html(data.content);
                    $("#view_level").html(data.level);
                    $("#view_status").html(data.status);
                    $("#view_result").html(data.result);
                    $("#view_endtime").html(data.endtime);
                }
            });
        }

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('demandTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.id;
                }else{
                    params+="&ids="+item.id;
                }
            });
            layer.confirm('真的删除选中的这些需求吗', function(index){
                //向服务端发送删除指令
                $.post("${ctx}/demand/deleteBatchDemand.action",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

    });
</script>
</body>
</html>
