package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.UI;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleUITest {

    PrintStream output = voidPrintStream();

    @Test
    public void test() {

        InputStream input = valueTypedByUser("3");
        UI userInterface = new ConsoleUI(input, output);

        int result = userInterface.getSelectedMove();
        assertThat(result, is(3));

    }

    public InputStream valueTypedByUser(String ... values) {

        String result = StringUtils.join(values, "\n");

        try {
            return new ByteArrayInputStream(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Encoding was not supported!");
            throw new RuntimeException();
        }
    }

    public PrintStream voidPrintStream() {
        return new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        });
    }


}
