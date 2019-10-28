//加载显示区域
$(function(){  //加载事件
    //使用datagrid绑异服务器数据展示
    $('#dg').datagrid({
        title:"区域信息",
        url:'getDistrictByPage',
        toolbar:"#tb",  //绑定工具栏
        pagination:true,
        pageSize:5,
        pageList:[5,10,15,20],
        columns:[[
            {field:'opt',checkbox:"true",title:'编号',width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'dd',title:'操作',width:100,
                formatter: function(value,row,index){
                    //同步
                    return "<a href='javascript:delDistrict("+row.id+");'>删除</a> | <a href=''>修改</a> | <a href=''>街道详情</a>";
                }
            },
        ]]
    });

});

//点击添加按钮调用的函数
function add(){
    //alert("打开添加窗口");
    $("#AddDialog").dialog("setTitle","添加区域");
    $("#AddDialog").dialog("open"); //打开  close 关闭
}

//关闭通过id关闭窗口
function CloseDialog(id){
    $("#"+id).dialog("close"); //打开  close 关闭
}

//保存区域
function SaveDialog(){
    //1.使用jquery的post方法发送异步请求实现添加
    /*  $.post("addDistrit",{"name":$("#name").val()},function(data){
          if(data.result==1){
              $("#dg").datagrid('reload'); //刷新
              $("#AddDialog").dialog("close");//关闭窗口
          }else{
              alert("添加失败!!!");
          }
      },"json");*/

    //2.使用easyui的表单提示插件
    $('#addForm').form('submit',{   //提交按钮
        url:"addDistrit",
        success:function(data){ //获得是json字符串
            //将json字符串转化为json对象
            data=$.parseJSON(data);
            if(data.result==1){
                $("#dg").datagrid('reload'); //刷新
                $("#AddDialog").dialog("close");//关闭窗口
            }else{
                alert("添加失败!!!");
            }
        }
    });
}

/*打开修改的对话框*/;
function goupdate(){
    //判断用户是否选择行
    //获取dagagrid选中的行
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==1){
        //获取当前行的编号--》查询当前记录-->还原表单
        //1.获取当前的编号
        var bh=SelectRows[0].id;
        //2.发送异步请求获取服务器数据
        $.post("getDistrit",{"id":bh},function(data){
            //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
            $("#upForm").form('load',data);
        },"json");
        //获得行对象的数据加载到表单中显示
        //$("#upForm").form('load',{"名秒":值,"名称":值});

        $("#upDialog").dialog("setTitle","修改区域");
        $("#upDialog").dialog("open"); //打开  close 关闭
    }else{
        //消息框 alert
        $.messager.alert('提示信息','你没有选中行或者选中多行','warning');
    }
}

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

/*删除区域*/
function delDistrict(id){
    //确认提示框
    $.messager.confirm('删除区域', '真的想删除吗?', function(r){
        if (r){
            //删除
            $.post("delDistrit",{"id":id},function(data){
                alert(data.result);
                if(data.result>=1){
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert('提示信息','删除失败!','error');
                }
            },"json");
        }
    });
}

/*批量删除区域*/
function DeleteMoreDistrict(){
    //获取dagagrid选中的行
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==0){
        $.messager.alert('提示信息', '你还没有选择删除项',"info");
    }else
    {
        $.messager.confirm('删除区域', '真的想删除吗?', function(r){
            if (r) {
                //拼接删除的字符串
                var ids = "";
                for (var i = 0; i < SelectRows.length; i++) {
                    ids = ids + SelectRows[i].id + ",";
                }
                ids = ids.substring(0, ids.length - 1);

                //发送异步请求删除
                //删除   ids值的格式是:1006,1007..
                $.post("delMoreDistrit",{"ids":ids},function(data){
                    if(data.result>=1){
                        $("#dg").datagrid('reload'); //刷新
                    }else{
                        $.messager.alert('提示信息','删除失败!','error');
                    }
                },"json");

            }
        });
    }
}