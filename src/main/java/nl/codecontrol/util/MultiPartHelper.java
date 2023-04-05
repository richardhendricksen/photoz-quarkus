package nl.codecontrol.util;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class MultiPartHelper {

    public MultiPartFile extractData(MultipartFormDataInput input) throws IOException {
        List<InputPart> inParts = input.getFormDataMap().get("data");
        if (inParts == null || inParts.isEmpty())
            throw new IllegalStateException("No data found");

        var inPart = inParts.get(0);

        // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
        var headers = inPart.getHeaders();
        var contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        var fileName = Arrays.stream(contentDispositionHeader)
            .filter(i -> i.trim().startsWith("filename"))
            .findFirst()
            .orElseThrow()
            .split("=")[1].trim().replaceAll("\"", "");

        return new MultiPartFile(fileName, inPart.getBody(byte[].class, null), inPart.getMediaType());
    }
}
