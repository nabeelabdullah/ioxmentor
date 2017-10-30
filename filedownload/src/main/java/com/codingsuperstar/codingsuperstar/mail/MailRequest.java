package com.codingsuperstar.codingsuperstar.mail;

import com.codingsuperstar.codingsuperstar.entity.Template;
import com.codingsuperstar.codingsuperstar.repo.TemplateRepo;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by nabeelabdullah on 30/10/17.
 */
@Component
public class MailRequest {

    @Autowired
    private TemplateRepo templateRepo;

    private String makeTemplate(String content, Map<String, String> data) throws Exception {
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        StringReader reader = new StringReader(content);
        SimpleNode node = runtimeServices.parse(reader, "Template name");
        org.apache.velocity.Template template = new org.apache.velocity.Template();
        template.setRuntimeServices(runtimeServices);
        template.setData(node);
        template.initDocument();
        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext(data);
        template.merge(context, writer);
        return writer.toString();
    }

    public String getContent(String name, Map<String, String> data) throws Exception {
        Template template = templateRepo.findByName(name);
        if (template == null)
            throw new Exception();
        return makeTemplate(template.getTemplate(), data);
    }
}