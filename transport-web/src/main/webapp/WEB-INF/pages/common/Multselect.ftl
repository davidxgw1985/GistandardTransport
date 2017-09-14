<!--
1、在需要用到单选框的页面引入
< #import "common/multSelect.ftl" as mySelect/>
2、在使用单选框时，如下图：
< @mySelect.select id="sex" datas={"0":"男","1":"女"} value="1,2"/>
< @mySelect.select id="address" datas=["北京","天津","上海"]/>
< @mySelect.select id="sex" datas=["选择性别","男","女"] value="男"/>

< @mySelect.select id="stanReceipt" datas=destComStations class="" value="SGX" key="staCode" text="staName" headkey="-1" headtext="--请选择--"/>
id:select控件的id和name属性，
datas：select控件的数据源，一般为一个对象列表，
class：select控件的样式，可不填
value：select控件的默认值，会在对象列表中查询是否有该值的对象，有则选中该对象，
key:select控件选中的值
text：select控件选中显示的文字，
headkey：select控件最上行的值，可不填，
headtext：select控件最上行显示的文字，比如：请选择，可不填
-->

<#macro select id datas class="" value="" key="" text="" headkey="" headtext="">
    <#assign x = value?split(",") />
    <#if class!="">
    <select class="${class}" id="${id}" name="${id}" multiple="multiple" >
    <#else >
    <select id="${id}" name="${id}" multiple="multiple" >
    </#if>
    <#if headkey!="">
        <option value="${headkey}">${headtext}</option>
    </#if>

    <#if datas?is_hash_ex>
        <#local keys=datas?keys/>
        <#list keys as key>
            <#if x?seq_contains(key)>
                <option value="${key}" selected>${datas[key]}</option>
            <#else>
                <option value="${key}">${datas[key]}</option>
            </#if>
        </#list>
    <#else>
        <#list datas as data>
            <#assign y = 1>
            <#list x as a>
                <#if a ==data[key]>
                    <#assign y = 2>
                </#if>

            </#list>
            <#if y==2>
                <option value="${data[key]}" selected>${data[text]}</option>
            <#else>
                <option value="${data[key]}">${data[text]}</option>
            </#if>
            <#assign y = 1>
        </#list>
    </#if>
</select>
</#macro>