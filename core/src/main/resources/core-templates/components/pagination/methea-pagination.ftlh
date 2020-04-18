<#-- ##### -->
<#-- @page #Pagination #Define pagination class -->
<#-- @url #String #Define url pagination do request -->
<#-- ##### -->

<#--
# Author : DKSilverX
# Date : 4/14/2020
#-->
<#macro pagination page url>
    <hr>
    <div id="custom-pagination">
        <div id="pagination-footer">
            <label style="font-size: 12px">Rows per page &nbsp;<select style="height: calc(2.1rem + 2px);"
                                                                       id="page-size"
                                                                       onchange="onSizeChange()"
                                                                       name="dataTable_length"
                                                                       aria-controls="dataTable"
                                                                       class="form-control input-sm">
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
            </label>
            <div style="display: inline-block;" class="clearfix pull-right float-right">
                <ul id="m-standard-pagination" class="pagination pull-right"></ul>
            </div>
        </div>
    </div>
    <script>
        // initial pagination
        let pageNumber = ${page['page']};
        let pageSize = ${page['size']};
        let totalPages = ${page['totalPages']};

        $(document).ready(function () {
            $('#page-size').val(pageSize.toString());

            $('#m-standard-pagination').twbsPagination({
                totalPages: totalPages,
                startPage: pageNumber,
                visiblePages: totalPages > 5 ? 5 : totalPages,
                initiateStartPageClick: false,
                href: false,
                hrefVariable: '{{number}}',

                // carousel-style pagination
                loop: false,

                // call back function click on pagination
                onPageClick: function (event, page) {
                    pageNumber = page;
                    onPageChange();
                },

                // pagination classes
                paginationClass: 'pagination',
                nextClass: 'next',
                prevClass: 'prev',
                lastClass: 'last',
                firstClass: 'first',
                pageClass: 'page',
                activeClass: 'active',
                disabledClass: 'disabled'
            });

            $('#m-standard-pagination').find('li').addClass('page-item');
        });

        function onSizeChange() {
            window.location.href = '${contextPath}${url}?page=' + pageNumber + '&size=' + $('#page-size').val();
        }

        function onPageChange() {
            window.location.href = '${contextPath}${url}?page=' + pageNumber + '&size=' + pageSize;
        }
    </script>
</#macro>