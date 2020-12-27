package processes.fibers;

import org.apache.commons.cli.CommandLine;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import processes.AbstractProcess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PdfMergeFiber extends AbstractMergeFiber {

    public PdfMergeFiber(AbstractProcess process) {
        super(process);
    }

    @Override
    public void process(CommandLine cli) {
        List<String> mergeTargets = cli.getArgList();

        if (mergeTargets.isEmpty()) {
            System.out.println("Command hasn't file arguments to merge");
            return;
        }

        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        List<File> mergedFiles = new ArrayList<>();
        for (String pathToTarget : mergeTargets) {
            File target = new File(pathToTarget);
            if (target.isFile()) {

                if (!target.getName().endsWith(".pdf")) {
                    System.out.printf("%s file is not pdf", target.getName());
                    return;
                }

                try {
                    mergedFiles.add(target);
                    mergerUtility.addSource(target);
                    System.out.printf("%s merged%n", target);
                } catch (FileNotFoundException e) {
                    if (cli.hasOption("s") || cli.hasOption("stacktrace")) {
                        e.printStackTrace();
                    } else {
                        System.out.println("Merge process failed");
                    }
                }
            } else {
                System.out.println(String.format("%s is not file", pathToTarget));
                return;
            }
        }
    }
}
