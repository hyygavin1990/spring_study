<!DOCTYPE html>
<html>

<head>
</head>
<link href="/static/bootstrap.min.css" rel="stylesheet">
<body >
<div class="container">
    <h3>groupid:${groupid}</h3>

    <table class="table table-bordered">
    <#list datasList as datas>
        <#if datas_index==0>
        <tr>
            <#list datas as data>
               <th>${data}</th>
            </#list>
        </tr>
        </#if>
        <#if datas_index!=0>
        <tr>
            <#list datas as data>
               <td>${data}</td>
            </#list>
        </tr>
        </#if>
    </#list>
    </table>
</div>

</body>
</html>
