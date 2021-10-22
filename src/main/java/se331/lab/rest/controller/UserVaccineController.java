package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.service.EventService;
import se331.lab.rest.service.UserVaccineService;
import se331.lab.rest.util.LabMapper;

@Controller
public class UserVaccineController {

    @Autowired
    UserVaccineService userVaccineService;

    @GetMapping("admin")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<UserVaccine> pageOutput;
        if (title == null) {
            pageOutput = userVaccineService.getUserVaccines(perPage, page);
        } else {
            pageOutput = userVaccineService.getUserVaccines(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserVaccineDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
}
