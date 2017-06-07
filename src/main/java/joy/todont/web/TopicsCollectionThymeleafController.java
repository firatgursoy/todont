package joy.todont.web;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.datatables.ConvertedDatatablesData;
import io.springlets.data.web.datatables.Datatables;
import io.springlets.data.web.datatables.DatatablesColumns;
import io.springlets.data.web.datatables.DatatablesPageable;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import joy.todont.domain.Topic;
import joy.todont.service.api.TopicService;
import joy.todont.web.reports.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;

/**
 * = TopicsCollectionThymeleafController
 TODO
 *
 */
@Controller
@RequestMapping(value = "/topics", name = "TopicsCollectionThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class TopicsCollectionThymeleafController {
    /**
     * TODO
     *
     */
    private MessageSource messageSource;
    /**
     * TODO
     *
     */
    private MethodLinkBuilderFactory<TopicsItemThymeleafController> itemLink;
    /**
     * TODO
     *
     */
    private ConversionService conversionService;
    /**
     * TODO
     *
     */
    private TopicService topicService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param topicService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public TopicsCollectionThymeleafController(TopicService topicService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setTopicService(topicService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(TopicsItemThymeleafController.class));
    }

    /**
     * TODO
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * It delegates in the `export` method providing the necessary information
     * to generate a PDF report.
     *
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportPdf", value = "/export/pdf")
    @ResponseBody
    public ResponseEntity<?> exportPdf(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsPdfExporter(), "topics_report.pdf", locale);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * It delegates in the `export` method providing the necessary information
     * to generate a XLS report.
     *
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportXls", value = "/export/xls")
    @ResponseBody
    public ResponseEntity<?> exportXls(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsXlsExporter(), "topics_report.xls", locale);
        return ResponseEntity.ok().build();
    }

    /**
     * This method contains all the entity fields that are able to be displayed in a
     * report. The developer could add a new column to the report builder providing the
     * field name and the builder where the new field will be added as column.
     *
     * @param columnName the field name to show as column
     * @param builder The builder where the new field will be added as column.
     */
    public void addColumnToReportBuilder(String columnName, FastReportBuilder builder, Locale locale, String fileName) {
        try {
        if (columnName.equals("id")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_id", null, "Id", locale), "id", Long.class.getName(), 50);
        }
        else if (columnName.equals("version")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_version", null, "Version", locale), "version", Integer.class.getName(), 100);
        }
        else if (columnName.equals("name")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_name", null, "Name", locale), "name", String.class.getName(), 100);
        }
        else if (columnName.equals("createdDate")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_createddate", null, "Created Date", locale), "createdDate", Calendar.class.getName(), 100);
        }
        else if (columnName.equals("createdBy")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_createdby", null, "Created By", locale), "createdBy", String.class.getName(), 100);
        }
        else if (columnName.equals("modifiedDate")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_modifieddate", null, "Modified Date", locale), "modifiedDate", Calendar.class.getName(), 100);
        }
        else if (columnName.equals("modifiedBy")) {
            builder.addColumn(getMessageSource().getMessage("label_topic_modifiedby", null, "Modified By", locale), "modifiedBy", String.class.getName(), 100);
        }
        }
        catch (ColumnBuilderException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException",
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()},
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
        catch (ClassNotFoundException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException",
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()},
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
    }

    /**
     * Method that obtains the filtered and ordered records using the Datatables information and
     * export them to a new report file. (It ignores the current pagination).
     *
     * To generate the report file it uses the `DynamicJasper` library
     * (http://dynamicjasper.com). This library allows developers to generate reports dynamically
     * without use an specific template to each entity.
     *
     * To customize the appearance of ALL generated reports, you could customize the
     * "export_default.jrxml" template located in "src/main/resources/templates/reports/". However,
     * if you want to customize the appearance of this specific report, you could create a new
     * ".jrxml" file and provide it to the library replacing the `builder.setTemplateFile();`
     * operation used in this implementation.
     *
     * @param search GlobalSearch that contains the filter provided by the Datatables component.
     * @param pageable Pageable that contains the Sort info provided by the Datatabes component.
     * @param datatablesColumns Columns displayed in the Datatables component.
     * @param response The HttpServletResponse.
     * @param exporter An specific JasperReportsExporter to be used during export process.
     * @param fileName The final filename to use.
     * @param locale The current Locale in the view context.
     */
    public void export(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, String[] datatablesColumns, HttpServletResponse response, JasperReportsExporter exporter, String fileName, Locale locale) {
        // Obtain the filtered and ordered elements
        Page<Topic> topics = getTopicService().findAll(search, pageable);

        // Prevent generation of reports with empty data
        if (topics == null || topics.getContent().isEmpty()) {
            return;
        }

        // Creates a new ReportBuilder using DynamicJasper library
        FastReportBuilder builder = new FastReportBuilder();

        // IMPORTANT: By default, this application uses "export_default.jrxml"
        // to generate all reports. If you want to customize this specific report,
        // create a new ".jrxml" template and customize it. (Take in account the
        // DynamicJasper restrictions:
        // http://dynamicjasper.com/2010/10/06/how-to-use-custom-jrxml-templates/)
        builder.setTemplateFile("templates/reports/export_default.jrxml");

        // The generated report will display the same columns as the Datatables component.
        // However, this is not mandatory. You could edit this code if you want to ignore
        // the provided datatablesColumns
        if (datatablesColumns != null) {
            for (String column : datatablesColumns) {
                // Delegates in addColumnToReportBuilder to include each datatables column
                // to the report builder
                addColumnToReportBuilder(column, builder, locale, fileName);
            }
        }

        // This property resizes the columns to use full width page.
        // Set false value if you want to use the specific width of each column.
        builder.setUseFullPageWidth(true);

        // Creates a new Jasper Reports Datasource using the obtained elements
        JRDataSource ds = new JRBeanCollectionDataSource(topics.getContent());

        // Generates the JasperReport
        JasperPrint jp;
        try {
            jp = DynamicJasperHelper.generateJasperPrint(builder.build(), new ClassicLayoutManager(), ds);
        }
        catch (JRException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException",
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()},
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }

        // Converts the JaspertReport element to a ByteArrayOutputStream and
        // write it into the response stream using the provided JasperReportExporter
        try {
            exporter.export(jp, fileName, response);
        }
        catch (JRException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException",
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()},
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
        catch (IOException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException",
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()},
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
    }

    /**
     * TODO
     *
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(Model model) {
        populateForm(model);

        model.addAttribute("topic", new Topic());
        return new ModelAndView("topics/create");
    }

    /**
     * TODO
     *
     * @param topic
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@Valid @ModelAttribute Topic topic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateForm(model);

            return new ModelAndView("/topics/create");
        }
        Topic newTopic = getTopicService().save(topic);
        UriComponents showURI = getItemLink().to(TopicsItemThymeleafLinkFactory.SHOW).with("topic", newTopic.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("createdDate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        model.addAttribute("modifiedDate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    /**
     * TODO
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    @ResponseBody
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {

        getTopicService().delete(ids);

        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param search
     * @param pageable
     * @param locale
     * @return ResponseEntity
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Topic>> select2(GlobalSearch search, Pageable pageable, Locale locale) {
        Page<Topic> topics = getTopicService().findAll(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Topic> select2Data = new Select2DataWithConversion<Topic>(topics, idExpression, getConversionService());
        return ResponseEntity.ok(select2Data);
    }

    /**
     * TODO
     *
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

    /**
     * TODO
     *
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO
     *
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * TODO
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<TopicsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO
     *
     * @param dataBinder
     */
    @InitBinder("topic")
    public void initTopicBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * TODO
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<TopicsItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO
     *
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(produces = Datatables.MEDIA_TYPE, name = "datatables", value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Topic>> datatables(DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Topic> topics = getTopicService().findAll(search, pageable);
        long totalTopicsCount = topics.getTotalElements();
        if (search != null && StringUtils.isNotBlank(search.getText())) {
            totalTopicsCount = getTopicService().count();
        }
        ConvertedDatatablesData<Topic> datatablesData = new ConvertedDatatablesData<Topic>(topics, totalTopicsCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(datatablesData);
    }

    /**
     * It delegates in the `export` method providing the necessary information
     * to generate a CSV report.
     *
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportCsv", value = "/export/csv")
    @ResponseBody
    public ResponseEntity<?> exportCsv(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsCsvExporter(), "topics_report.csv", locale);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "list")
    public ModelAndView list(Model model) {
        return new ModelAndView("topics/list");
    }

    /**
     * TODO
     *
     * @return TopicService
     */
    public TopicService getTopicService() {
        return topicService;
    }

    /**
     * TODO
     *
     * @param topicService
     */
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }
}
