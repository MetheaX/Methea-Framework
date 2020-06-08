<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Datatable Configuration">
        <@mtable.table thead=tableHead tdata=datatable tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/datatable" id="tbl-datatable">
        </@mtable.table>

        <@mpagination.pagination page=pagination url="/app/datatable"></@mpagination.pagination>
    </@template.main>
</#compress>