package hello.advanced.trace;

public class TraceStatus {

    // 내부에 트랜잭션ID와 level을 가지고 있다.
    private TraceId traceId;

    // 로그 시작시간.
    private Long startTimeMs;

    // 시작시 사용한 메시지
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
