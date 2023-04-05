package nl.codecontrol.util;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MultiPartHelper {

    public MultiPartFile extractData(MultipartFormDataInput input) throws IOException {
        String fileName = "";

        final Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inPart = formParts.get("data");

        InputPart inputPart = inPart.stream().findFirst().orElseThrow();

        // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
        MultivaluedMap<String, String> headers = inputPart.getHeaders();
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                fileName = tmp[1].trim().replaceAll("\"", "");
            }
        }

        return new MultiPartFile(fileName, inputPart.getBody(byte[].class, null), inputPart.getMediaType());
    }
}
