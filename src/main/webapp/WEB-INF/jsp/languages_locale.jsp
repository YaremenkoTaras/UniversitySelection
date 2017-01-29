<div class="languages">
    <c:choose>
        <%-- When user hasn't explicitly set language,
             render toggle according to browser's preferred locale --%>
        <c:when test="${!empty sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}">
            <c:set var="currentLocale"
                   value='${sessionScope["javax.servlet.jsp.jstl.fmt.locale.session"]}' scope='page'/>
        </c:when>

        <%-- Otherwise, render widget according to the set locale --%>
        <c:otherwise>
            <c:set var='currentLocale' value='${pageContext.request.locale.language}' scope='page'/>
        </c:otherwise>
    </c:choose>

    <c:forTokens items="en,ru" delims="," var="lang">
        <c:choose>
            <c:when test="${currentLocale eq lang}">
                <div class="bubble">
                    <fmt:message key='language.${lang}'/>
                </div>
            </c:when>
            <c:otherwise>
                <c:url var="url" value="/Controller">
                    <c:param name="command" value="changeLanguage"/>
                    <c:param name="language" value="${lang}"/>
                </c:url>
                <div class="bubble">
                    <a data-name="l10n" href="${url}"><fmt:message key='language.${lang}'/></a>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forTokens>
</div>