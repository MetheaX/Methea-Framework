<#import "../../components/template.ftlh" as template>
<#import "../../components/table/methea-standard-table.ftlh" as mtable>
<#import "../../components/modal/methea-standard-modal.ftlh" as mmodal>
<#import "../../components/form/methea-standard-form.ftlh" as mform>
<#import "../../components/input/methea-standard-input.ftlh" as minput>
<#import "../../components/input/methea-hidden-input.ftlh" as mhiddeninput>
<#import "../../components/pagination/methea-pagination.ftlh" as mpagination>
<#compress>
    <@template.main title="Menu Configuration">
        <@mtable.table thead=tableHead tdata=menu tcolumns=tableColumns filters=tableFilterColumns
        dataFilters=dataFilters urlFilter="/app/menu" id="tbl-menu">
        </@mtable.table>

        <@mpagination.pagination page=pagination url="/app/menu"></@mpagination.pagination>
    </@template.main>
</#compress>