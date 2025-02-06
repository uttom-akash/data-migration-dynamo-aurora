package com.bkash.savings.models.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Fintech {
	
	@NotNull
	private String[] notRetryableErrorCodes;
	
	@NotNull
	@Valid
	private FintechProxy proxy = new FintechProxy();
	
	@NotNull
	@Valid
    private ChannelCode channelCode = new ChannelCode();
	
	@NotNull
	@Valid
	private CommandId commandId = new CommandId();

	@Getter
	@Setter
	@ToString
	public static class FintechProxy {
		@Valid
        @NotNull
        private Integer timeout;
		
		@Valid
        @NotBlank
        private String url;
	}
	
	@Getter
    @Setter
    @ToString
    public static class ChannelCode {
        @NotBlank
        private String customer;

        @NotBlank
        private String agent;

        @NotBlank
        private String merchant;
    }
	
	@Getter
    @Setter
    @ToString
	public static class CommandId {
		@NotBlank
		private String fdr;
		
		@NotBlank
		private String dpsGeneral;
		
		@NotBlank
		private String  dpsIslamic;
		
		@NotBlank
		private String queryTransaction;
	}
}
