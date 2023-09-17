package org.staimov.controller;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.staimov.domain.Status;
import org.staimov.domain.Task;
import org.staimov.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        int totalPages = (int) Math.ceil((double) taskService.count() / limit);
        List<Task> tasks = taskService.getPage((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "tasks";
    }

    @PostMapping("/{id}")
    public void edit(Model model,
                        @PathVariable Integer id,
                        @RequestBody TaskInfo info) {

        Preconditions.checkArgument(id != 0 && id > 0, "Invalid id");
        taskService.edit(id, info.getDescription(), info.getStatus());
    }

    @PostMapping("/")
    public void create(Model model,
                     @RequestBody TaskInfo info) {
        taskService.create(info.getDescription(), info.getStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(Model model,
                     @PathVariable Integer id) {

        Preconditions.checkArgument(id != 0 && id > 0, "Invalid id");
        taskService.delete(id);
    }
}
