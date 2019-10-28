<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
         //加载显示区域
         $(function(){  //加载事件
              //显示数据
             $('#dg').datagrid({
                 title:"区域信息",
                 url:"getDistrictBypage",
                 toolbar:"#tb",//绑定工具栏
              pagination:true,
                 pageSize:5,
                 pageList:[5,10,15,20],
                 columns:[[
                     {field:'opt',checkbox:"true", title:'编号',width:100},
                     {field:'id', title:'编号',width:100},
                     {field:'name',title:'区域名称',width:100},
                     {field:'dd', title:'操作',width:100,
                     formatter:function (value, row, index) {
                         //同步
                          return "<a href='javascript:delDistrict("+row.id+");'>删除</a>";
                     }
                     },
                 ]]
             });

         });
         //点击添加按钮时调用的函数
        function add() {
           // alert("打开 添加窗口")
            $("#addDialog").dialog("setTitle","添加区域");
            $("#addDialog").dialog("open"); //打开  close关闭
        }
        //通过id关闭对话框
        function CloseDialog(id) {
            $("#"+id).dialog("close");
        }
     //添加区域
         function SaveDialog(){
             $.ajax({
                 type:"post",
                 url:"addDisrict",
                 data:$("#addForm").serialize(),
                 dataType:"json",
                 success:function (result) {
                     if(result){
                         alert("添加成功!");
                         CloseDialog();
                     }
                 },
                 error:function () {
                     alert("添加请求失败!");
                 }
             })
             }


             /*修改  */
       function goupdate() {
            //判断是否进行选择
           //获取dagagrid选中的行
           var SelectRows = $("#dg").datagrid('getSelections');
           if(SelectRows.length==1){
               //获取当前行的编号--》查询当前记录-->还原表单
               //1.获取当前的编号
               var bh=SelectRows[0].id;
               //2.发送异步请求获取服务器数据
               $.post("getDistrict",{"id":bh},function(data){
                   //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
                   $("#upForm").form('load',data);
               },"json");
               //获得行对象的数据加载到表单中显示
               //$("#upForm").form('load',{"名秒":值,"名称":值});


               $("#upDialog").dialog("setTitle","修改区域");
           $("#upDialog").dialog("open"); //打开  close关闭
       } else {
           //消息框
               $.messager.alert('提示信息','你没有选中行或者选中多行','warning');
           }
       }
             /*
             * 实现修改
             * */
         /*实现修改区域*/
         function updateDistrict(){
             //使用easuy提交表单
             $('#upForm').form('submit',{   //提交按钮
                 url:"upDistrit",
                 success:function(data){ //获得是json字符串
                     //将json字符串转化为json对象
                     data=$.parseJSON(data);
                     if(data.result==1){
                         $("#dg").datagrid('reload'); //刷新
                         $("#upDialog").dialog("close");//关闭窗口
                     }else{
                         $.messager.alert('提示信息','修改失败!','error');
                     }
                 }
             });
         }




    /*
    * 删除区域
    * */
         function delDistrict(id){
             //确认提示框
             $.messager.confirm('删除区域', '真的想删除吗?', function(r){
                 if (r){
                     //删除
                     $.post("delDistrit",{"id":id},function(data){
                         if(data.result==1){
                             $("#dg").datagrid('reload'); //刷新
                         }else{
                             $.messager.alert('提示信息','删除失败!','error');
                         }
                     },"json");
                 }
             });
         }


    // 批量删除
    function DeleteMoreDistrict() {
             /*获取当前的 选着项*/
        var SelectRows = $("#dg").datagrid('getSelections');
        if (SelectRows.length==0){
            $.messager.alert('提示信息', '还没有选着删除项!',"info");
        }else {
            $.messager.confirm('删除区域', '真的想删除吗?', function(r){
                if (r){
                    var ids=[];
                     for (var i=0; i<SelectRows.length;i++ ){
                        ids.push(SelectRows[i].id);
                     }
                     $.ajax({
                         type:"post",
                         url:"/admin/delMoreDistrit?ids="+ids,
                         data:"",
                         success:function (result) {
                             $.messager.alert("系统提示","成功删除"+result+"条数据!","info");
                             $("#dg").datagrid('reload');
                         },
                         error:function () {
                             $.messager.alert("系统提示","批量删除请求失败!","info");
                         }
                     })
                }
            });
        }


    }
    </script>



</head>
<body>
<table id="dg"></table>

<%--工具栏--%>
<div id="tb">
    <a href="javascript:add()" class="easyui-linkbutton"
       iconCls="icon-add" plain="true">添加</a> <a
        href="javascript:goupdate()" class="easyui-linkbutton"
        iconCls="icon-edit" plain="true">修改</a> <a
        href="javascript:DeleteMoreDistrict()" class="easyui-linkbutton"
        iconCls="icon-remove" plain="true">批量删除</a>
</div>
<%--添加区域对话框--%>
<div id="addDialog" class="easyui-dialog"  style="width:300px;height:300px;" buttons="#AddDialogButtons"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,draggable:true">
    <form action="" id="addForm" name="addForm" method="post">
        区域名称:<input type="text" name="name"><br/>
    </form>
</div>
<!--对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">添加</a> <a href="javascript:CloseDialog('addDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>




<%--修改区域对话框--%>
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  data-options="modal:true">
    <form action="" id="upForm" name="upForm" method="post">
        区域名称:<input type="text" name="name"><br/>
        <input type="hidden"   name="id"><br/>

    </form>
</div>
<!--对话框的按钮-->
<div id="upDialogButtons">
    <a href="javascript:updateDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseDialog('upDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
