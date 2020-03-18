<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/2/27
  Time: 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form layui-row changePwd" id="changePwd">
    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
        <div class="layui-input-block layui-red pwdTips">初始密码为“123456”，新密码必须两次输入一致才能提交</div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="hidden" name="userid" value="${user.userid}">
                <input type="hidden" name="loginname" value="${user.loginname}">
                <input type="text"  value="${user.realname }" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-block">
                <input type="password" name="oldPwd" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" value="" name="pwd" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" value="" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changePwd" id="doSearch">立即修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript" >
    layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laydate = layui.laydate,
            laytpl = layui.laytpl,
            table = layui.table;

      /*  $(document).ready(function(){
            $.post("/Handler/CaseHandler.ashx?type=get", { Id: id }, function (data) {
                curEntity = data;
                form.val('', data);
            });
        })*/
        form.on('submit(changePwd)', function(data){
            $.post("${ctx}/user/updateUserPwd.action",data.field,function(res){
                layer.msg(res.msg);
                /*//刷新数据 表格
                tableIns.reload();*/
            });
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //添加验证规则
        form.verify({
            oldPwd : function(value, item){
                //user.pwd加密过 不能直接判断，待修改
              /*  if(value != "123456"){
                    return "密码错误，请重新输入！";
                }*/
            },
            newPwd : function(value, item){
                if(value.length < 6){
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd : function(value, item){
                if(!new RegExp($("#oldPwd").val()).test(value)){
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        })
    })
</script>
</body>
</html>
