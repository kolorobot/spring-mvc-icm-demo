package com.github.kolorobot.icm.incident;

import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.files.File;
import com.github.kolorobot.icm.files.FilesRepository;
import com.github.kolorobot.icm.support.web.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/incident")
public class FilesController {

    @Inject
    private FilesRepository filesRepository;

    @Transactional
    @RequestMapping(value = "/{id}/file")
    public String upload(@PathVariable("id") Long id,
                         @RequestParam("file") MultipartFile file,
                         User user,
                         RedirectAttributes redirectAttributes) {

        String redirectUrl = "redirect:/incident/{id}";

        if (file.isEmpty()) {
            MessageHelper.addErrorAttribute(redirectAttributes, "file.empty");
            return redirectUrl;
        }

        com.github.kolorobot.icm.files.File theFile = new com.github.kolorobot.icm.files.File();
        theFile.setName(file.getOriginalFilename());
        theFile.setContentType(file.getContentType());
        theFile.setSize(file.getSize());
        theFile.setCreatorId(user.getAccountId());
        theFile.setCreated(new Date());
        theFile.setObjectId(id);
        theFile.setObjectType("incident");

        try {
            filesRepository.save(theFile, file.getBytes());
            MessageHelper.addSuccessAttribute(redirectAttributes, "file.uploaded", theFile.getName());
            return redirectUrl;
        } catch (Exception e) {
            MessageHelper.addErrorAttribute(redirectAttributes, "file.upload.error", e.getMessage());
            return redirectUrl;
        }
    }

    @RequestMapping(value = "/{id}/file/{fileId}")
    public void download(@PathVariable("id") long id,
                         @PathVariable("fileId") long fileId,
                         HttpServletResponse response) throws IOException {

        File file = filesRepository.getFile(fileId);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName() + "\"");
        response.setHeader("Content-Type", file.getContentType());
        filesRepository.writeFileTo(fileId, response.getOutputStream());
    }
}
