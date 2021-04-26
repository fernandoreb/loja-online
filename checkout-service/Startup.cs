using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using checkout_service.Models;
using checkout_service.Services;


namespace checkout_service
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.Configure<CartDatabaseSettings>(Configuration.GetSection(nameof(CartDatabaseSettings)));
            services.AddSingleton<ICartDatabaseSettings>(sp=>sp.GetRequiredService<IOptions<CartDatabaseSettings>>().Value);

            services.Configure<OrderDatabaseSettings>(Configuration.GetSection(nameof(OrderDatabaseSettings)));
            services.AddSingleton<IOrderDatabaseSettings>(sp=>sp.GetRequiredService<IOptions<OrderDatabaseSettings>>().Value);

            services.Configure<BackendServiceSettings>(Configuration.GetSection(nameof(BackendServiceSettings)));
            services.AddSingleton<IBackendServiceSettings>(sp=>sp.GetRequiredService<IOptions<BackendServiceSettings>>().Value);

            services.AddSingleton<CartService>();
            services.AddSingleton<OrderService>();

            services.AddControllers();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
