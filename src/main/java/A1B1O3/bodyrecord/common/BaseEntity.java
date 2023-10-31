package A1B1O3.bodyrecord.common;

import A1B1O3.bodyrecord.common.type.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static A1B1O3.bodyrecord.common.type.StatusType.*;
import static javax.persistence.EnumType.STRING;


@Getter
@MappedSuperclass //BaseEntity를 상속한 엔티티들은 아래 필드를 칼럼으로 인식
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //자동으로 값 매핑 기능 추가
public abstract class
BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private StatusType state = USEABLE;

    protected BaseEntity(final StatusType state) {
        this.state = state;
    }

    public boolean isDeleted() {
        return this.state.equals(DELETED);
    }

    public void changeStatusToDeleted() {
        this.state = DELETED;
    }

}
