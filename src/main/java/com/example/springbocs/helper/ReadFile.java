package com.example.springbocs.helper;


import com.example.springbocs.exception.FileExtensionNotSupportedException;
import com.example.springbocs.model.dto.LostItemDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<LostItemDto> processFile(String fileName) throws IOException {
        String filePath = "src/main/java/com/example/springbocs/entry/";
        String fileExtension = extensionChecker(fileName);

        File file = new File(filePath + fileName);

        switch (fileExtension) {
            case "pdf":
                return pdfConverter(file);
            default:
                throw new FileExtensionNotSupportedException("currently only pdf are supported");
        }
    }

    public List<LostItemDto> pdfConverter(File file) throws IOException {
        List<LostItemDto> lostItemDtoList = new ArrayList<>();
        LostItemDto lostItemDto = new LostItemDto();

        PDDocument doc = PDDocument.load(file);
        PDFTextStripper docExtract = new PDFTextStripper();

        String text = docExtract.getText(doc).toLowerCase();

        doc.close(); //flushing io

        String[] items = text.split("\n"); //somehow \\r\\n\\r\\n is not recognizing enter

        int itemCounter = 0; //since each item only contain itemName, quantity, and place
        for (String item : items) {
            item = item.replace(" ", "");
            if (item.contains(":")) {
                String[] keyValues = item.split(":");
                String key = keyValues[0];
                String value = keyValues[1].trim();

                switch (key) {
                    case "itemname":
                        lostItemDto.setItemName(value);
                        break;
                    case "quantity":
                        try{
                            lostItemDto.setItemAmount(Integer.valueOf(value));
                        } catch (NumberFormatException e){}
                        break;
                    case "place":
                        lostItemDto.setItemLocation(value);
                        break;
                    default:
                }
                itemCounter++;
                if (itemCounter % 3 == 0) {//reach 3rd info in each item
                    lostItemDtoList.add(lostItemDto);
                    lostItemDto = new LostItemDto();
                }
            }
        }
        return lostItemDtoList;
    }

    public String extensionChecker(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

//    public static void main(String[] args) throws IOException {
//        ReadFile readFile = new ReadFile();
//        List<LostItemDto> cur = readFile.processFile("entry1.pdf");
//        for (LostItemDto lostItemDto : cur) {
//            System.out.println(lostItemDto.getItemName());
//            System.out.println(lostItemDto.getItemAmount());
//            System.out.println(lostItemDto.getItemLocation());
//        }
//    }

}
