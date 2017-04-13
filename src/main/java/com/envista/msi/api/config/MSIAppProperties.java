package com.envista.msi.api.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/*import com.envista.msi.api.config.JHipsterProperties.Security.Authentication;
import com.envista.msi.api.config.JHipsterProperties.Security.Authentication.Oauth;*/

/**
 * @author SANKER
 *
 */
@ConfigurationProperties(prefix = "msiapp", ignoreUnknownFields = false)
public class MSIAppProperties {
	private final Http http = new Http();
	private final Mail mail = new Mail();
	private final Security security = new Security();
	private final CorsConfiguration cors = new CorsConfiguration();
	private final Ribbon ribbon = new Ribbon();

	public Http getHttp() {
		return http;
	}

	public Mail getMail() {
		return mail;
	}

	public Security getSecurity() {
		return security;
	}

	public CorsConfiguration getCors() {
		return cors;
	}

	public Ribbon getRibbon() {
		return ribbon;
	}

	public static class Http {

		private final Cache cache = new Cache();

		public Cache getCache() {
			return cache;
		}

		public static class Cache {

			private int timeToLiveInDays = 1461;

			public int getTimeToLiveInDays() {
				return timeToLiveInDays;
			}

			public void setTimeToLiveInDays(int timeToLiveInDays) {
				this.timeToLiveInDays = timeToLiveInDays;
			}
		}
	}

	public static class Mail {

		private String from = "msi@localhost";

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}
	}
	
    public static class Security {

        private final Authentication authentication = new Authentication();

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private final Oauth oauth = new Oauth();

            public Oauth getOauth() {
                return oauth;
            }

            public static class Oauth {

                private String clientid;

                private String secret;
                
                private String host;

                private Integer port;

                private int tokenValidityInSeconds = 1800;

                public String getClientid() {
                    return clientid;
                }

                public void setClientid(String clientid) {
                    this.clientid = clientid;
                }

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public int getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

				public String getHost() {
					return host;
				}

				public void setHost(String host) {
					this.host = host;
				}

				public Integer getPort() {
					return port;
				}

				public void setPort(Integer port) {
					this.port = port;
				}
            }
        }
    }

	private final Logging logging = new Logging();

	public Logging getLogging() {
		return logging;
	}

	public static class Logging {

		private final Logstash logstash = new Logstash();

		public Logstash getLogstash() {
			return logstash;
		}

		public static class Logstash {

			private boolean enabled = false;

			private String host = "localhost";

			private int port = 5000;

			private int queueSize = 512;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}

			public int getQueueSize() {
				return queueSize;
			}

			public void setQueueSize(int queueSize) {
				this.queueSize = queueSize;
			}
		}

	}

	public static class Ribbon {

		private String[] displayOnActiveProfiles;

		public String[] getDisplayOnActiveProfiles() {
			return displayOnActiveProfiles;
		}

		public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles) {
			this.displayOnActiveProfiles = displayOnActiveProfiles;
		}
	}

}
