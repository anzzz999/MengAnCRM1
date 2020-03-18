
<%--
  Created by IntelliJ IDEA.
  User: 詹 明 威
  Date: 2020/2/27
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料--layui后台管理模板 2.0</title>
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
<form class="layui-form layui-row">
    <div class="layui-col-md6 layui-col-xs12">
        <div class="layui-form-item">
            <label class="layui-form-label">登录账号</label>
            <div class="layui-input-block">
                <input type="hidden" name="userid" value="${user.userid}">
                <input type="text" name="loginname" value="${user.loginname}" disabled class="layui-input layui-disabled">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" name="realname" value="${user.realname}" disabled class="layui-input layui-disabled">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-block">
                <input type="text" name="identity" value="${user.identity}" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block userSex">
                <c:if test="${user.sex ==1}" >
                    <input type="radio" name="sex" value="1" title="男" checked="">
                    <input type="radio" name="sex" value="0" title="女">
                </c:if>
                <c:if test="${user.sex ==0}" >
                    <input type="radio" name="sex" value="1" title="男" >
                    <input type="radio" name="sex" value="0" title="女" checked="">
                </c:if>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" value="${user.phone}" name="phone" placeholder="请输入手机号码" lay-verify="phone" class="layui-input userPhone">
            </div>
        </div>

        <div class="layui-form-item ">
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-block">
                <input type="text" value="${user.address}" name="address" placeholder="请输入家庭住址"  class="layui-input ">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
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


        //提交数据
        form.on('submit(changeUser)', function(data){
            $.post("${ctx}/user/updateUser.action",data.field,function(res){
                layer.msg(res.msg);
                /*//刷新数据 表格
                tableIns.reload();*/
            });

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    })
</script>
<!--<script type="text/javascript" src="userInfo.js"></script>
<script type="text/javascript" src="../../js/cacheUserInfo.js"></script>-->
</body>
</html>
