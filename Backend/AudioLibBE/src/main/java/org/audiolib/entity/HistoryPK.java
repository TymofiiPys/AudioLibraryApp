package org.audiolib.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryPK implements Serializable {
    private String userId;
    private long audioId;
}
