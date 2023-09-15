package org.staimov.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.staimov.domain.Status;

@Getter
@Setter
@ToString
public class TaskInfo {
    private String description;
    private Status status;
}
