<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/select/methea-standard-select.ftlh" as mselect>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Permissions">
        <div class="table-responsive">
            <@mtable.table thead=tableHead tdata=permissions tcolumns=tableColumns filters=tableFilterColumns
            dataFilters=dataFilters urlFilter="/app/permissions" id="tbl-permissions">
            </@mtable.table>
        </div>
    </@template.main>
    <@mpagination.pagination page=pagination url="/app/permissions"></@mpagination.pagination>
</#compress>