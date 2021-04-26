namespace checkout_service.Models
{
    public class BackendServiceSettings : IBackendServiceSettings
    {
        public string CartUrl { get; set; }
        public string WorkflowUrl { get; set; }
        public string WorkflowBasicAuth { get; set; }
    }

    public interface IBackendServiceSettings
    {
        string CartUrl { get; set; }
        string WorkflowUrl { get; set; }
        string WorkflowBasicAuth { get; set; }
    }
}