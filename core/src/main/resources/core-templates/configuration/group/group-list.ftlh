<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<@template.main title="Groups">
    <div class="table-responsive">
        <@mtable.table thead=tableHead tdata=groups tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/groups" id="tbl-groups">
        </@mtable.table>
    </div>
    <@mpagination.pagination page=pagination url="/app/groups"></@mpagination.pagination>
</@template.main>

<script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/resources/vendor/sweetalert/sweetalert.min.js"></script>
<script src="${contextPath}/resources/vendor/jquery/jquery.twbsPagination.min.js"></script>