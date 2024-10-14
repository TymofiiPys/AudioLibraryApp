package org.audiolib.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryPK implements Serializable {
    private String userId;
    private long audioId;
}
